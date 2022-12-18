package kg.fhome.test.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtDTO {

    private String token;

    private String type = "Bearer";

    private Long id;

    private String email;

    public JwtDTO(String token, Long id,String email) {
        this.token = token;
        this.id = id;
        this.email = email;
    }

}
