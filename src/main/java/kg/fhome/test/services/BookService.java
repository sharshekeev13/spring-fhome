package kg.fhome.test.services;

import kg.fhome.test.entity.Book;
import kg.fhome.test.repository.BookRepository;
import kg.fhome.test.services.interfaces.BookServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BookService implements BookServiceInterface {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBookByOwnerId(Long id) {
        return bookRepository.findByOwnerId(id);
    }

    @Override
    public List<Book> getAllBookByUserId(Long id) {
        return bookRepository.findByUserId(id);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
}
