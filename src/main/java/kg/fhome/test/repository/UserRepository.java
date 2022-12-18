package kg.fhome.test.repository;

import jakarta.transaction.Transactional;
import kg.fhome.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmailIdIgnoreCase(String emailId);
    Optional<User> findByEmailId(String emailId);
}
