package kg.fhome.test.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl-users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String emailId;
    @NotBlank
    private boolean business;
    private String photo;
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
    private boolean isEnabled;
}
