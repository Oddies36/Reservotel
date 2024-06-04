package be.reservotel.reservotel.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.reservotel.reservotel.DTO.HotelDTO;
import be.reservotel.reservotel.DTO.HotelPaysDTO;
import be.reservotel.reservotel.DTO.HotelVilleDTO;
import be.reservotel.reservotel.Services.HotelService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    
    @GetMapping("/countries")
    public ResponseEntity<List<HotelPaysDTO>> getHotelCountries() {
        List<HotelPaysDTO> countries = hotelService.getPaysHotel();
        return ResponseEntity.ok(countries);
    }
    
    @GetMapping("/{pays}/villes")
    public ResponseEntity<List<HotelVilleDTO>> getVillesHotel(@PathVariable String pays) {
        List<HotelVilleDTO> villes = hotelService.getVillesHotel(pays);
        return ResponseEntity.ok(villes);
    }

    @GetMapping("/{pays}/{ville}")
    public ResponseEntity<List<HotelDTO>> getHotelsWithoutBudget(@PathVariable String pays, @PathVariable String ville) {
        List<HotelDTO> hotels = hotelService.getHotels(pays, ville);
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/{pays}/{ville}/{budget}")
    public ResponseEntity<List<HotelDTO>> getHotelsWithBudget(@PathVariable String pays, @PathVariable String ville, @PathVariable double budget) {
        List<HotelDTO> hotels = hotelService.getHotelsWithBudget(pays, ville, budget);
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/{idHotel}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long idHotel) {
        HotelDTO hotel = hotelService.getHotelById(idHotel);
        return ResponseEntity.ok(hotel);
    }
    
}
