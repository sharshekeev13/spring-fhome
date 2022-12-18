package kg.fhome.test.controllers;

import kg.fhome.test.repository.ItemRepository;
import jakarta.validation.Valid;
import kg.fhome.test.dto.item.ItemCreate;
import kg.fhome.test.entity.Item;
import kg.fhome.test.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/items")
@PreAuthorize("isAuthenticated()")
public class ItemController {

    private final ItemService itemService;
    public ItemController(ItemService itemService,
                          ItemRepository itemRepository){
        this.itemService = itemService;
    }

    @GetMapping
    public ArrayList<Item> getAll(){
        return itemService.getAll();
    }


    @PostMapping
    public Item create(
                @Valid ItemCreate itemCreate,
                @RequestParam(value = "first_image", required = false) MultipartFile first_image,
                @RequestParam(value = "second_image", required = false) MultipartFile second_image,
                @RequestParam(value = "third_image", required = false) MultipartFile third_image

    ) throws IOException {
        if(itemCreate.getCategories().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if(itemCreate.getTitle().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if(itemCreate.getDescription().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return itemService.create(first_image,second_image,third_image,itemCreate);
    }

    @GetMapping("/{id}")
    public Item getById(@PathVariable("id") Long id) throws IOException {
        Optional<Item> item = itemService.findById(id);
        if(item.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return item.get();
    }

    @PostMapping("/update")
    public Item update(@RequestBody Item item){
        return itemService.update(item);
    }

    @GetMapping("/confirm-item/{id}")
    public Item confirmItem(@PathVariable("id") Long id){
        Optional<Item> item = itemService.findById(id);
        if(item.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        item.get().setConfirmed(true);
        return itemService.update(item.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseStatusException delete(@PathVariable("id") Long id)throws IOException{
        Optional<Item> item = itemService.findById(id);
        if(item.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        itemService.delete(id);
        return new ResponseStatusException(HttpStatus.ACCEPTED);
    }

    @GetMapping("/high-price")
    public ArrayList<Item> filterHighPrice(){
        return itemService.filterHighPrice();
    }

    @GetMapping("/low-price")
    public ArrayList<Item> filterLowPrice(){
        return itemService.filterLowPrice();
    }

    @GetMapping("/new-items")
    public ArrayList<Item> getByDateNew(){
        return itemService.getItemByNewDate();
    }

    @GetMapping("old-items")
    public ArrayList<Item> getByDateOld(){
        return itemService.getItemByOldDate();
    }

    @GetMapping("/search")
    public ArrayList<Item> searchItems(@RequestParam("query") String query){
        return itemService.searchItem(query);
    }

    @GetMapping("/not-confirm")
    public ArrayList<Item> getNotConfirmItems(){
        return itemService.getNotConfirmItems();
    }
}
