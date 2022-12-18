package kg.fhome.test.dto.auth;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ModRegisterDTO {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotBlank
        private String emailId;
        @NotBlank
        private String password;
        @NotBlank
        private String firstName;
        @NotBlank
        private String secondName;
        @NotBlank
        private int age;
        @NotBlank
        private String role;
        @NotBlank
        private String phoneNumber;
        @NotBlank
        private String userInfo;
}
