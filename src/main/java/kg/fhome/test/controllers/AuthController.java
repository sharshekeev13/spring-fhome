package kg.fhome.test.controllers;


import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;
import kg.fhome.test.dto.MessageDTO;
import kg.fhome.test.dto.auth.JwtDTO;
import kg.fhome.test.dto.auth.LoginDto;
import kg.fhome.test.dto.auth.ModRegisterDTO;
import kg.fhome.test.entity.User;
import kg.fhome.test.repository.ConfirmationTokenRepository;
import kg.fhome.test.repository.UserRepository;
import kg.fhome.test.security.UserDetailsImpl;
import kg.fhome.test.security.jwt.util.JwtUtil;
import kg.fhome.test.services.AuthService;
import kg.fhome.test.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private AuthService authService;


    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/login")
    public JwtDTO login(@RequestBody LoginDto login) throws Exception {
        if(userRepository.findByEmailIdIgnoreCase(login.getEmailId()) != null){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getEmailId(), login.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtil.generateToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return new JwtDTO(token, userDetails.getId(), userDetails.getUsername());
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @Valid User user,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar) throws IOException {
        if(authService.registerUser(user,avatar)){
            return ResponseEntity.ok(new MessageDTO("Sending Email"));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register-mod")
    public ResponseEntity<?> registerMod(
                @RequestBody ModRegisterDTO user) throws IOException {
        if(user.getRole().equals("mod")){
            User res = authService.registerMod(user);
            return ResponseEntity.ok(res);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/confirm-account")
    public RedirectView confirmUserAccount(@RequestParam("token")String confirmationToken, ModelMap model){
        System.out.println(confirmationToken);
        authService.confirmUserAccount(confirmationToken);
        return new RedirectView("http://localhost:8080/index.html");
    }

}
