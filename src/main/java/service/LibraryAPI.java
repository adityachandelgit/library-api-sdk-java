package service;

import enums.BookDataSource;
import exception.EntityNotFoundException;
import model.Book;
import repository.BookRepo;
import repository.CloudBookRepo;
import repository.LocalBookRepo;

import java.util.List;

public class LibraryAPI {

    private BookRepo bookRepo;

    public LibraryAPI(BookDataSource bookDataSource) {
        if (bookDataSource.equals(BookDataSource.LOCAL)) {
            this.bookRepo = new LocalBookRepo();
        } else {
            this.bookRepo = new CloudBookRepo();
        }
    }

    public List<Book> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    public Book getBookById(int id) {
        return bookRepo.getBookById(id);
    }

    public Book createBook(Book book) {
        return bookRepo.createBook(book);
    }

    public Book updateBook(Book book) throws EntityNotFoundException {
        return bookRepo.updateBook(book);
    }

    public void deleteBook(int id) throws EntityNotFoundException {
        bookRepo.deleteBook(id);
    }

}
