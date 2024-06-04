package be.reservotel.reservotel.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.reservotel.reservotel.Model.Adresse;
import be.reservotel.reservotel.Repository.AdresseRepository;

@Service
public class AdresseService {
    
    @Autowired
    private AdresseRepository adresseRepository;

    public List<String> findRueByNumero(String numero){
        return adresseRepository.findRueByNumero(numero);
    }

    public List<Adresse> showAllRue(){

        List<Adresse> adresses = adresseRepository.findAll();

        return adresses;
    }
}
