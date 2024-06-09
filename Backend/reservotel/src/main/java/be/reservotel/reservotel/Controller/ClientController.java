package be.reservotel.reservotel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import be.reservotel.reservotel.DTO.AddrClientDTO;
import be.reservotel.reservotel.DTO.ClientDTO;
import be.reservotel.reservotel.DTO.ReservationDTO;
import be.reservotel.reservotel.Services.ClientService;
import be.reservotel.reservotel.Services.ReservationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ReservationService reservationService;


    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> createClient(@RequestBody AddrClientDTO addrClient) {
        if(clientService.writeClient(addrClient)){
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.badRequest().body("nok");
        }
    }

    @PostMapping("/saveclient")
    @ResponseBody
    public ResponseEntity<String> saveClient(@RequestBody ClientDTO addrClient) {
        if(clientService.saveClient(addrClient)){
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.badRequest().body("nok");
        }
    }

    @GetMapping("/profil/reservations")
    public ResponseEntity<List<ReservationDTO>> getReservations(@RequestParam String email, @RequestParam int page, @RequestParam int limit) {
        List<ReservationDTO> reservations = reservationService.getReservationsByClientEmail(email, page, limit);
        return ResponseEntity.ok(reservations);
    }
}