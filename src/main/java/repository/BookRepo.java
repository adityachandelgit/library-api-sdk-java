package repository;

import model.Book;

import java.util.List;

public interface BookRepo {

    List<Book> getAllBooks();

    Book getBookById(int id);

    Book createBook(Book book);

    Book updateBook(Book book);

    void deleteBook(int id);

}
