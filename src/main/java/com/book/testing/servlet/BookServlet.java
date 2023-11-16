package com.book.testing.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.testing.dao.BookDao;
import com.book.testing.dao.BookDaoImpl;
import com.book.testing.entity.Book;

@WebServlet("/")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final BookDao bookDao;

    public BookServlet() {
        super();
        this.bookDao = new BookDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertBook(request, response);
                break;
            case "/delete":
                deleteBook(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateBook(request, response);
                break;
            default:
                listBooks(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Book> books = bookDao.getAllBooks();
        request.setAttribute("listBook", books);
        forwardToPage("/jsp/list.jsp", request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forwardToPage("/jsp/register.jsp", request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = bookDao.selectBook(id);
        request.setAttribute("book", book);
        forwardToPage("/jsp/register.jsp", request, response);
    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Book newBook = createBookFromRequest(request);
        bookDao.insertBook(newBook);
        redirectToPage("list", response);
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Book updatedBook = createBookFromRequest(request);
        bookDao.updateBook(updatedBook);
        redirectToPage("list", response);
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookDao.deleteBook(id);
        redirectToPage("list", response);
    }

    private void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void redirectToPage(String page, HttpServletResponse response) throws IOException {
        response.sendRedirect(page);
    }

    private Book createBookFromRequest(HttpServletRequest request) {
        String idParam = request.getParameter("id");
        int id = (idParam != null && !idParam.isEmpty()) ? Integer.parseInt(idParam) : 0;
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        int price = Integer
                .parseInt(request.getParameter("price").length() >= 5 ? request.getParameter("price").substring(0, 5)
                        : request.getParameter("price"));
        return new Book(id, name, author, price);
    }
}