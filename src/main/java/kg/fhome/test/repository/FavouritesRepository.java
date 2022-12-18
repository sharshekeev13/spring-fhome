package kg.fhome.test.repository;

import kg.fhome.test.entity.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites,Long> {
    Favourites findByUserId(Long userId);
    List<Favourites> findAllByUserId(Long userId);
}
