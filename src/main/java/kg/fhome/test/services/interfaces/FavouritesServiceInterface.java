package kg.fhome.test.services.interfaces;

import kg.fhome.test.entity.Favourites;

import java.util.List;

public interface FavouritesServiceInterface {
    List<Favourites> getUserFavourites(Long id);
    Favourites createFavourites(Favourites favourites);
    Favourites updateFavourites(Favourites favourites, Long id);
}
