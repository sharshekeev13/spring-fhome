package kg.fhome.test.services.interfaces;

import kg.fhome.test.entity.Book;

import java.util.List;

public interface BookServiceInterface {
    List<Book> getAllBookByOwnerId(Long id);
    List<Book> getAllBookByUserId(Long id);
    void deleteBook(Long id);
    List<Book> getAll();
    Book createBook(Book book);
}
