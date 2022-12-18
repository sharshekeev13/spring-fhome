package kg.fhome.test.services;


import kg.fhome.test.entity.User;
import kg.fhome.test.repository.UserRepository;
import kg.fhome.test.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        User user = repository.findByEmailId(emailId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + emailId));
        return new UserDetailsImpl(user);
    }
}
