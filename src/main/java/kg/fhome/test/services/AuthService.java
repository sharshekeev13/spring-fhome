package kg.fhome.test.services;

import kg.fhome.test.dto.auth.LoginDto;
import kg.fhome.test.dto.auth.ModRegisterDTO;
import kg.fhome.test.entity.ConfirmationToken;
import kg.fhome.test.entity.User;
import kg.fhome.test.repository.ConfirmationTokenRepository;
import kg.fhome.test.repository.UserRepository;
import kg.fhome.test.services.interfaces.AuthServiceInterface;
import kg.fhome.test.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Objects;

@Service
public class AuthService implements AuthServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public User loginUser(LoginDto loginDto) throws Exception {
        User user = userRepository.findByEmailIdIgnoreCase(loginDto.getEmailId());
        if(user != null && user.getPassword().equals(loginDto.getPassword()) && user.isEnabled()){
            return user;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean registerUser(User user, MultipartFile avatar) throws IOException {
        User existingUser = userRepository.findByEmailIdIgnoreCase(user.getEmailId());
        if(existingUser != null)
        {
            return false;
        }
        else
        {
            user.setPhoto(uploadPhoto(avatar));
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmailId());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("fhome.official@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/auth/confirm-account?token="+confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage);
            return true;
        }
    }


    @Override
    public HttpStatus confirmUserAccount(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationTokenContains(confirmationToken);
        if(token != null)
        {
            User user = userRepository.findByEmailIdIgnoreCase(token.getUser().getEmailId());
            user.setEnabled(true);
            User savedUser = userRepository.save(user);
            return HttpStatus.ACCEPTED;
        }
        else
        {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @Override
    public User registerMod(ModRegisterDTO user) {
        User res = User.builder()
                .emailId(user.getEmailId())
                .password(user.getPassword())
                .business(false)
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .role("mod")
                .isEnabled(true)
                .age(user.getAge())
                .phoneNumber(user.getPhoneNumber())
                .build();
        return userRepository.save(res);
    }

    private String uploadPhoto(MultipartFile image) throws IOException {
        if(image != null){
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            String filecode = FileUploadUtil.saveFile(fileName, image);
            return "/downloadFile/" + filecode;
        }
        return null;
    }
}
