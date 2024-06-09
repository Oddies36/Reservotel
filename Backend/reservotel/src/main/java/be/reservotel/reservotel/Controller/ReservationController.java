package be.reservotel.reservotel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.reservotel.reservotel.DTO.CustomPannierDTO;
import be.reservotel.reservotel.Services.ReservationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    
    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@RequestBody CustomPannierDTO pannierDTO) {
        try{
            System.out.println("choix: " + pannierDTO.getChoixPointFidelite());
            reservationService.createReservation(pannierDTO);
            return ResponseEntity.ok("ok");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("nok");
        }
    }
    
    public ResponseEntity<String> createReservation(){
        return ResponseEntity.ok("ok");
    }
}
