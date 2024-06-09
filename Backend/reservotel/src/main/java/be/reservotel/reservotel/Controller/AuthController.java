package be.reservotel.reservotel.Controller;

import java.time.LocalDate;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.reservotel.reservotel.DTO.ClientDTO;
import be.reservotel.reservotel.Model.Client;
import be.reservotel.reservotel.Model.CustomUserDetails;
import be.reservotel.reservotel.Services.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private ClientService clientService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody ClientDTO clientDTO, HttpSession session) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(clientDTO.getEmail(), clientDTO.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Informations de connexion incorrectes");
        }
    }

    @PostMapping("/checkmail")
    public ResponseEntity<String> checkMail(@RequestBody String mail) {
        boolean emailExists = clientService.checkMailExists(mail);
        if (emailExists) {
            return ResponseEntity.ok("Mail existe");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mail n'existe pas");
        }
    }

    @GetMapping("/getuser")
    public ClientDTO getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Client client = userDetails.getClient();

        return new ClientDTO(client.getNom(), client.getEmail(), client.getPrenom(), client.getPointsFidelite(), client.getDateNaissance(), client.getNumeroTelephone(), client.getRole());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @GetMapping("/validation")
    public ResponseEntity<Void> validateSession(HttpSession session) {
        if (session != null && session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}