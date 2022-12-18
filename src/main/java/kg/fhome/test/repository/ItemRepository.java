package kg.fhome.test.repository;

import kg.fhome.test.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    List<Item> findByUserId(Long userId);

    List<Item> findAllByOrderByPriceDesc();

    List<Item> findAllByOrderByPriceAsc();

    List<Item> findAllByConfirmedTrue();

    List<Item> findAllByConfirmedFalse();

    List<Item> findAllByOrderByDateDesc();

    List<Item> findAllByOrderByDateAsc();

    @Query(value = "SELECT * FROM Items i WHERE i.title LIKE CONCAT('%',:query, '%') Or i.description LIKE CONCAT('%',:query, '%')",nativeQuery = true)
    List<Item> searchItem(String query);

}
