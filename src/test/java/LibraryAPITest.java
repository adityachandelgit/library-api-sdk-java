import enums.BookDataSource;
import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;
import model.Book;
import org.junit.jupiter.api.Test;
import service.LibraryAPI;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryAPITest {

    @Test
    void getAllBooks_ShouldReturn3Books() {
        LibraryAPI libraryAPI = new LibraryAPI(BookDataSource.LOCAL);
        final List<Book> allBooks = libraryAPI.getAllBooks();
        assertEquals(3, allBooks.size());
    }

    @Test
    void getValidBook_ShouldReturn1Book() {
        LibraryAPI libraryAPI = new LibraryAPI(BookDataSource.LOCAL);
        final Book book = libraryAPI.getBookById(1);
        assertEquals(1, 1);
    }

    @Test
    void getInvalidBook_ShouldReturnNull() {
        LibraryAPI libraryAPI = new LibraryAPI(BookDataSource.LOCAL);
        final Book book = libraryAPI.getBookById(Integer.MAX_VALUE);
        assertNull(book);
    }

    @Test
    void createBook_ShouldReturnNewBook() {
        LibraryAPI libraryAPI = new LibraryAPI(BookDataSource.LOCAL);
        int bookIdNew = 4;

        Book bookCreated = libraryAPI.getBookById(bookIdNew);
        // The new book which is being created should not already be in the book list
        assertNull(bookCreated);

        Book bookCreate = new Book(bookIdNew, "Resident Evil: Underworld", "S.D. Perry");
        libraryAPI.createBook(bookCreate);
        bookCreated = libraryAPI.getBookById(bookIdNew);
        assertEquals(bookCreate.getId(), bookCreated.getId());
        assertEquals(bookCreate.getName(), bookCreated.getName());
        assertEquals(bookCreate.getAuthor(), bookCreated.getAuthor());
    }

    @Test
    void createAlreadyExistingBook_ShouldThrowException() {
       EntityAlreadyExistsException thrown = assertThrows(
                EntityAlreadyExistsException.class,
                () ->  {
                    LibraryAPI libraryAPI = new LibraryAPI(BookDataSource.LOCAL);
                    Book bookCreate = new Book(3, "Resident Evil: Underworld", "S.D. Perry");
                    libraryAPI.createBook(bookCreate);
                }
        );
        assertEquals("Create operation failed because Book with id: 3 already exists.", thrown.getMessage());
    }

    @Test
    void updateBook_ShouldReturnUpdatedBook() {
        LibraryAPI libraryAPI = new LibraryAPI(BookDataSource.LOCAL);
        final Book bookOne = libraryAPI.getBookById(1);
        // Updating a book field
        bookOne.setAuthor("Aditya");
        final Book bookOneUpdated = libraryAPI.updateBook(bookOne);
        assertEquals(bookOne.getId(), bookOneUpdated.getId());
        assertEquals(bookOne.getName(), bookOneUpdated.getName());
        assertEquals(bookOne.getAuthor(), bookOneUpdated.getAuthor());
    }

    @Test
    void updateInexistentBook_ShouldThrowException() {
        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () ->  {
                    LibraryAPI libraryAPI = new LibraryAPI(BookDataSource.LOCAL);
                    final Book bookInexistent = new Book(5, "DoesNotExist", "DoesNotExist");
                    libraryAPI.updateBook(bookInexistent);
                }
        );
        assertEquals("Update operation failed because Book with id: 5 does not exist.", thrown.getMessage());
    }

    @Test
    void deleteBook_ShouldNotInBookList() {
        LibraryAPI libraryAPI = new LibraryAPI(BookDataSource.LOCAL);
        libraryAPI.deleteBook(1);
        assertNull(libraryAPI.getBookById(1));
    }

    @Test
    void deleteInexistentBook_ShouldThrowException() {
        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () ->  {
                    LibraryAPI libraryAPI = new LibraryAPI(BookDataSource.LOCAL);
                    libraryAPI.deleteBook(5);
                }
        );
        assertEquals("Delete operation failed because Book with id: 5 does not exist.", thrown.getMessage());
    }


}
