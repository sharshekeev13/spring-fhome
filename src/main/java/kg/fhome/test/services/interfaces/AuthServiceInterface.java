package kg.fhome.test.services.interfaces;

import kg.fhome.test.dto.auth.LoginDto;
import kg.fhome.test.dto.auth.ModRegisterDTO;
import kg.fhome.test.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AuthServiceInterface {
    User loginUser(LoginDto loginDto) throws Exception;
    boolean registerUser(User user, MultipartFile avatar) throws IOException;
    HttpStatus confirmUserAccount(String confirmationToken);
    User registerMod(ModRegisterDTO user);
}
