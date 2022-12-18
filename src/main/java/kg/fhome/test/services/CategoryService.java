package kg.fhome.test.services;

import kg.fhome.test.entity.Category;
import kg.fhome.test.exception.ResourceNotFoundException;
import kg.fhome.test.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }


    public ArrayList<Category> getAll(){
        return (ArrayList<Category>)categoryRepository.findAll();
    }

    public Category create(Category category){
        Category find = categoryRepository.findByNameContains(category.getName());
        if(find != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return categoryRepository.save(category);
    }

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

    public Category update(Category category){
        return categoryRepository.save(category);
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }
}
