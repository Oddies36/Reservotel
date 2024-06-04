package be.reservotel.reservotel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.reservotel.reservotel.DTO.ClientDTO;
import be.reservotel.reservotel.Services.ClientService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private ClientService clientService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody ClientDTO clientDTO){
        int checkCredentials = clientService.checkLoginInfo(clientDTO.getEmail(), clientDTO.getPassword());

        if(checkCredentials == 0){
            return ResponseEntity.ok("ok");
        } else if(checkCredentials == -1){
            return ResponseEntity.badRequest().body("emailNotExist");
        } else{
            return ResponseEntity.badRequest().body("passwordIncorrect");
        }


    }
}
