package repository;

import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;
import model.Book;

import java.util.*;

public class LocalBookRepo implements BookRepo {

    private Map<Integer, Book> booksMap = new HashMap<Integer, Book>();

    public LocalBookRepo() {
        booksMap.put(1, new Book(1, "Resident Evil: The Umbrella Conspiracy", "S.D. Perry"));
        booksMap.put(2, new Book(2, "Resident Evil: Caliban Cove", "S.D. Perry"));
        booksMap.put(3, new Book(3, "Resident Evil: City of the Dead", "S.D. Perry"));
    }

    public List<Book> getAllBooks() {
        return new ArrayList<Book>(booksMap.values());
    }

    public Book getBookById(int id) {
        return booksMap.get(id);
    }

    public Book createBook(Book book) {
        if (booksMap.containsKey(book.getId())) {
            throw new EntityAlreadyExistsException("Create operation failed because Book with id: " + book.getId() + " already exists.");
        }
        return booksMap.put(book.getId(), book);
    }

    public Book updateBook(Book book) {
        if (!booksMap.containsKey(book.getId())) {
            throw new EntityNotFoundException("Update operation failed because Book with id: " + book.getId() + " does not exist.");
        }
        return booksMap.put(book.getId(), book);
    }

    public void deleteBook(int id) {
        if (!booksMap.containsKey(id)) {
            throw new EntityNotFoundException("Delete operation failed because Book with id: " + id + " does not exist.");
        }
        booksMap.remove(id);
    }


}
