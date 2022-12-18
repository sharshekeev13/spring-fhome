package kg.fhome.test.repository;

import kg.fhome.test.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByUserId(Long id);
    List<Book> findByOwnerId(Long id);
}
