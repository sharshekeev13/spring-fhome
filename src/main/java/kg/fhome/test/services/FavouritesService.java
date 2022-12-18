package kg.fhome.test.services;

import kg.fhome.test.entity.Favourites;
import kg.fhome.test.repository.FavouritesRepository;
import kg.fhome.test.services.interfaces.FavouritesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FavouritesService implements FavouritesServiceInterface {

    @Autowired
    private FavouritesRepository favouritesRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<Favourites> getUserFavourites(Long id) {
        return favouritesRepository.findAllByUserId(id);
    }

    @Override
    public Favourites createFavourites(Favourites favourites) {
        return favouritesRepository.save(favourites);
    }

    @Override
    public Favourites updateFavourites(Favourites favourites, Long id) {
        if(userService.getUserById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return favouritesRepository.save(favourites);
    }
}
