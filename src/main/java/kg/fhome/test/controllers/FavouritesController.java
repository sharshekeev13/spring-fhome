package kg.fhome.test.controllers;

import kg.fhome.test.common.ApiResponse;
import kg.fhome.test.entity.Favourites;
import kg.fhome.test.entity.Item;
import kg.fhome.test.entity.User;
import kg.fhome.test.services.FavouritesService;
import kg.fhome.test.services.ItemService;
import kg.fhome.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/favourites")
@PreAuthorize("isAuthenticated()")
public class FavouritesController {

    @Autowired
    private FavouritesService favouritesService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public List<Item> getFavouritesById(@PathVariable("id") Long id){
        List<Favourites> body = favouritesService.getUserFavourites(id);
        List<Item> items = new ArrayList<>();
        for(Favourites favourites : body){
            items.add(favourites.getItem());
        }
        return items;
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> create(@RequestBody Item item, @PathVariable("id") Long id){
        Optional<User> user = userService.getUserById(id);
        if(user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Favourites fav = new Favourites(user.get(),item);
        favouritesService.createFavourites(fav);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public Favourites update(@RequestBody Favourites favourites, @PathVariable("id") Long id){
        return favouritesService.updateFavourites(favourites,id);
    }
}
