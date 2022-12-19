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
    private String emailId;
    private boolean business;
    private String photo;
    private String password;
    private String firstName;
    private String secondName;
    private int age;
    private String role;
    private String phoneNumber;
    private String userInfo;
    private boolean isEnabled;
}
