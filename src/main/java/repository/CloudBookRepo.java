package repository;

import model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloudBookRepo implements BookRepo {

    @Override
    public List<Book> getAllBooks() {
        // Todo: Get data from cloud via REST
        return null;
    }

    @Override
    public Book getBookById(int id) {
        // Todo: Get data from cloud via REST
        return null;
    }

    @Override
    public Book createBook(Book book) {
        // Todo: Get data from cloud via REST
        return null;
    }

    @Override
    public Book updateBook(Book book) {
        // Todo: Get data from cloud via REST
        return null;
    }

    @Override
    public void deleteBook(int id) {
        // Todo: Get data from cloud via REST
    }
}
