package be.reservotel.reservotel.Services;

import be.reservotel.reservotel.Model.Client;
import be.reservotel.reservotel.Model.CustomUserDetails;
import be.reservotel.reservotel.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(email);
        if (client == null) {
            throw new UsernameNotFoundException("Client pas trouvé");
        }

        return new CustomUserDetails(client);
    }
}