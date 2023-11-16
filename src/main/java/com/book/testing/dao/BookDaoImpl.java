package com.book.testing.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.testing.entity.Book;

public class BookDaoImpl implements BookDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";

    private static final String INSERT_BOOKS_SQL = "INSERT INTO book (name, author, price) VALUES (?, ?, ?)";
    private static final String SELECT_BOOK_BY_ID = "SELECT * FROM book WHERE id = ?";
    private static final String SELECT_ALL_BOOKS = "SELECT * FROM book";
    private static final String DELETE_BOOKS_SQL = "DELETE FROM book WHERE id = ?";
    private static final String UPDATE_BOOKS_SQL = "UPDATE book SET name = ?, author = ?, price = ? WHERE id = ?";

    public BookDaoImpl() {
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }

    @Override
    public void insertBook(Book book) {
        System.out.println(INSERT_BOOKS_SQL);
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKS_SQL)) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDouble(3, book.getPrice());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                int price = rs.getInt("price");
                books.add(new Book(id, name, author, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void deleteBook(int id) {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_BOOKS_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book selectBook(int id) {
        Book book = null;
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String author = rs.getString("author");
                int price = rs.getInt("price");
                book = new Book(id, name, author, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void updateBook(Book book) {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_BOOKS_SQL)) {
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getPrice());
            statement.setInt(4, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}