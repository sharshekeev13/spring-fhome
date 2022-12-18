package kg.fhome.test.repository;

import kg.fhome.test.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationTokenContains(String confirmationToken);
    }
