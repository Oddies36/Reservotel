package be.reservotel.reservotel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import be.reservotel.reservotel.DTO.AddrClientDTO;
import be.reservotel.reservotel.Services.ClientService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/")
public class ClientController {

    @Autowired
    private ClientService clientService;
    
    // @PostMapping("/register")
    // @ResponseBody
    // public void createClient(@RequestBody AddrClientDTO addrClient) { // @RequestBody permet de prendre le json avec info et convertir en client
    //     clientService.writeClient(addrClient);
    // }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> createClient(@RequestBody AddrClientDTO addrClient) {
        if(clientService.writeClient(addrClient)){
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.badRequest().body("nok");
        }
    }
}
