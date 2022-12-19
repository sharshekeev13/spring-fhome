package kg.fhome.test.dto.auth;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ModRegisterDTO {
        private String emailId;
        private String password;
        private String firstName;
        private String secondName;
        private int age;
        private String role;
        private String phoneNumber;
        private String userInfo;
}
