package kg.fhome.test.controllers;

import kg.fhome.test.entity.Category;
import kg.fhome.test.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@PreAuthorize("isAuthenticated()")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<Category> create(@RequestBody Category category){
        Category newOne = categoryService.create(category);
        return new ResponseEntity<>(newOne, HttpStatus.CREATED);
    }

    @GetMapping("/category")
    public ArrayList<Category> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/category/{id}")
    public Category getById(@PathVariable("id") Long id) throws IOException {
        Optional<Category> category = categoryService.findById(id);
        if(category.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return category.get();
    }

    @PostMapping("/category/update/")
    public Category update(@RequestBody Category category){
        return categoryService.update(category);
    }

    @DeleteMapping("category/delete/{id}")
    public ResponseStatusException delete(@PathVariable("id") Long id) throws IOException{
        Optional<Category> category = categoryService.findById(id);
        if(category.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        categoryService.delete(id);
        return new ResponseStatusException(HttpStatus.ACCEPTED);
    }

}
