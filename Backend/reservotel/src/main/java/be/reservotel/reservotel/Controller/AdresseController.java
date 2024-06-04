package be.reservotel.reservotel.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.reservotel.reservotel.Model.Adresse;
import be.reservotel.reservotel.Services.AdresseService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
// Tout les methodes vont utiliser le chemin /adresse comme base dans l'url
@RequestMapping("/adresse")
public class AdresseController {

    private AdresseService adresseService;
    
    public AdresseController(AdresseService adresseService){
        this.adresseService = adresseService;
    }

    @GetMapping("/numero")
    public List<String> getRueByNumero(@RequestParam String numero) {
        List<String> rues = this.adresseService.findRueByNumero(numero);

        return rues;
    }

    @GetMapping("/sirine")
    public List<Adresse> getAllRues() {
        List<Adresse> rues = this.adresseService.showAllRue();

        return rues;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
