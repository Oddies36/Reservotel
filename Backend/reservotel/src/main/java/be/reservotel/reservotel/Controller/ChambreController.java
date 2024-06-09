package be.reservotel.reservotel.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.reservotel.reservotel.DTO.ChambreDTO;
import be.reservotel.reservotel.Services.ChambreService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/chambre")
public class ChambreController {

    @Autowired
    private ChambreService chambreService;
    
    @GetMapping("/{idHotel}/total")
    public ResponseEntity<List<ChambreDTO>> getHotelCountries(@PathVariable Long idHotel) {
        List<ChambreDTO> chambres = chambreService.getChambresByIdHotel(idHotel);
        return ResponseEntity.ok(chambres);
    }

    @GetMapping("/{idHotel}/dispo")
    public ResponseEntity<List<ChambreDTO>> getHotelCountriesDispo(@PathVariable Long idHotel) {
        List<ChambreDTO> chambres = chambreService.getChambresDispoByIdHotel(idHotel);
        return ResponseEntity.ok(chambres);
    }
    
    
    
}
