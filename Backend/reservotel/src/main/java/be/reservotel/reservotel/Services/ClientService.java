package be.reservotel.reservotel.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import be.reservotel.reservotel.DTO.AddrClientDTO;
import be.reservotel.reservotel.DTO.ClientDTO;
import be.reservotel.reservotel.Model.Adresse;
import be.reservotel.reservotel.Model.Client;
import be.reservotel.reservotel.Repository.AdresseRepository;
import be.reservotel.reservotel.Repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean writeClient(AddrClientDTO addrClient) {

        Adresse adresse = new Adresse();
        adresse.setRue(addrClient.getAdresse().getRue());
        adresse.setNumero(addrClient.getAdresse().getNumero());
        adresse.setBoite(addrClient.getAdresse().getBoite());
        adresse.setCodePostal(addrClient.getAdresse().getCodePostal());
        adresse.setVille(addrClient.getAdresse().getVille());
        adresse.setPays(addrClient.getAdresse().getPays());

        Adresse savedAdresse = adresseRepository.save(adresse);

        Client client = new Client();
        String encodedPassword = passwordEncoder.encode(addrClient.getClient().getPassword());
        client.setPassword(encodedPassword);
        client.setNom(addrClient.getClient().getNom());
        client.setPrenom(addrClient.getClient().getPrenom());
        client.setDateNaissance(addrClient.getClient().getDateNaissance());
        client.setEmail(addrClient.getClient().getEmail());
        client.setNumeroTelephone(addrClient.getClient().getNumeroTelephone());
        client.setPointsFidelite(0);
        client.setAdresse(savedAdresse);
        client.setRole("USER");

        clientRepository.save(client);
        return true;
    }

    public int checkLoginInfo(String email, String password) {
        int emailReturned = clientRepository.getEmail(email);
        String userPassword = "";

        if (emailReturned > 0) {
            userPassword = clientRepository.getPasswordByEmail(email);
            if (userPassword.equals(password)) {
                return 0;
            } else {
                return -2;
            }
        } else {
            return -1;
        }
    }

    public boolean checkMailExists(String email) {
        int emailExists = clientRepository.getEmail(email);
        if (emailExists > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean saveClient(ClientDTO addrClient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveClient'");
    }
}