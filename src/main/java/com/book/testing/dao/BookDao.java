package com.book.testing.dao;

import java.util.List;

import com.book.testing.entity.Book;

public interface BookDao {
    public void insertBook(Book book);

    public List<Book> getAllBooks();

    public Book selectBook(int id);

    public void updateBook(Book book);

    public void deleteBook(int id);
}
