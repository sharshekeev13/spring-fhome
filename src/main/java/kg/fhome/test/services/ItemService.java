package kg.fhome.test.services;


import kg.fhome.test.dto.item.ItemCreate;
import kg.fhome.test.entity.Category;
import kg.fhome.test.entity.Item;
import kg.fhome.test.entity.User;
import kg.fhome.test.repository.CategoryRepository;
import kg.fhome.test.repository.ItemRepository;
import kg.fhome.test.repository.UserRepository;
import kg.fhome.test.utils.FileUploadUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ItemService(ItemRepository itemRepository,
                       UserRepository userRepository,
                       CategoryRepository categoryRepository){
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public ArrayList<Item> getAll(){
        return (ArrayList<Item>)itemRepository.findAllByConfirmedTrue();
    }

    public Item create(MultipartFile first_image,MultipartFile second_image,MultipartFile third_image, ItemCreate itemCreate) throws IOException {
        LocalDate date = LocalDate.now();
        Item item = Item.builder()
                .title(itemCreate.getTitle())
                .description(itemCreate.getDescription())
                .date(date)
                .price(Integer.parseInt(itemCreate.getPrice()))
                .city(itemCreate.getCity())
                .address(itemCreate.getAddress())
                .first_photo(uploadPhoto(first_image))
                .second_photo(uploadPhoto(second_image))
                .third_photo(uploadPhoto(third_image))
                .confirmed(false)
                .build();
        Optional<User> user = userRepository.findById(itemCreate.getUserId());
        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<Category> categoryList = new ArrayList<>();
        List<String> list = itemCreate.getCategories();
        if(list.size() != 0){
            for(String cat : list){
                Category category = categoryRepository.findByNameContains(cat);
                if(category != null) categoryList.add(category);
                else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        item.setCategories(categoryList);
        item.setUserId(itemCreate.getUserId());
        return itemRepository.save(item);
    }

    public Optional<Item> findById(Long id){
        return itemRepository.findById(id);
    }

    public Item update(Item item){
        return itemRepository.save(item);
    }

    public void delete(Long id){
        itemRepository.deleteById(id);
    }

    public ArrayList<Item> filterHighPrice(){ return (ArrayList<Item>)itemRepository.findAllByOrderByPriceDesc(); }

    public ArrayList<Item> filterLowPrice(){ return (ArrayList<Item>)itemRepository.findAllByOrderByPriceAsc(); }

    public ArrayList<Item> getItemByNewDate() {return (ArrayList<Item>) itemRepository.findAllByOrderByDateDesc(); }

    public ArrayList<Item> getItemByOldDate() {return (ArrayList<Item>) itemRepository.findAllByOrderByDateAsc(); }

    public ArrayList<Item> searchItem(String query) {return (ArrayList<Item>) itemRepository.searchItem(query); }

    public ArrayList<Item> getNotConfirmItems() { return (ArrayList<Item>) itemRepository.findAllByConfirmedFalse(); }

    private String uploadPhoto(MultipartFile image) throws IOException {
        if(image != null){
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            String filecode = FileUploadUtil.saveFile(fileName, image);
            return "/downloadFile/" + filecode;
        }
        return null;
    }
}
