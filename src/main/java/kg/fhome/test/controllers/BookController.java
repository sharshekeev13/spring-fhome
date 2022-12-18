package kg.fhome.test.controllers;


import kg.fhome.test.dto.book.BookCreateDto;
import kg.fhome.test.entity.Book;
import kg.fhome.test.entity.Item;
import kg.fhome.test.entity.User;
import kg.fhome.test.services.BookService;
import kg.fhome.test.services.ItemService;
import kg.fhome.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@PreAuthorize("isAuthenticated()")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<Book> getAll(){
        return bookService.getAll();
    }
    @GetMapping("/book/owner/{id}")
    public List<Book> getByOwner(@PathVariable("id") Long id){
        return bookService.getAllBookByOwnerId(id);
    }
    @GetMapping("/book/user/{id}")
    public List<Book> getByUser(@PathVariable("id") Long id){
        return bookService.getAllBookByUserId(id);
    }
    @DeleteMapping("/book/{id}")
    public ResponseStatusException deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
        return new ResponseStatusException(HttpStatus.ACCEPTED);
    }
    @PostMapping("")
    public Book createBook(@RequestBody BookCreateDto bookDTO){
        Optional<User> user = userService.getUserById(bookDTO.getUserId());
        Optional<User> owner = userService.getUserById(bookDTO.getOwnerId());
        Optional<Item> item = itemService.findById(bookDTO.getItemId());
        if(user.isEmpty() || owner.isEmpty() || item.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Book book = new Book(user.get(),owner.get(),item.get(),bookDTO.getNote());
        return bookService.createBook(book);
    }
}
