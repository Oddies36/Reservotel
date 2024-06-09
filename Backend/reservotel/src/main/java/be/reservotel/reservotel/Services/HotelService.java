package be.reservotel.reservotel.Services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.reservotel.reservotel.DTO.HotelDTO;
import be.reservotel.reservotel.DTO.HotelPaysDTO;
import be.reservotel.reservotel.DTO.HotelVilleDTO;
import be.reservotel.reservotel.DTO.OptionDTO;
import be.reservotel.reservotel.Model.Equipement;
import be.reservotel.reservotel.Repository.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<HotelPaysDTO> getPaysHotel() {
        List<String> list = new ArrayList<String>();
        List<HotelPaysDTO> listePays = new ArrayList<HotelPaysDTO>();

        list = hotelRepository.getHotelPays();

        for (String pays : list){
            HotelPaysDTO hp = new HotelPaysDTO();
            hp.setPays(pays);
            listePays.add(hp);
        }

        return listePays;
    }

    public List<HotelVilleDTO> getVillesHotel(String pays) {
        List<String> list = new ArrayList<String>();
        List<HotelVilleDTO> listeVilles = new ArrayList<HotelVilleDTO>();

        list = hotelRepository.getHotelVilles(pays);

        for (String ville : list){
            HotelVilleDTO hv = new HotelVilleDTO();
            hv.setVille(ville);
            listeVilles.add(hv);
        }

        return listeVilles;
    }

    public List<HotelDTO> getHotels(String pays, String ville) {
        List<HotelDTO> hotels = new ArrayList<HotelDTO>();
        hotels = hotelRepository.getHotels(pays, ville);

        return hotels;
    }

    public List<HotelDTO> getHotelsWithBudget(String pays, String ville, double budget) {
        List<HotelDTO> hotels = new ArrayList<HotelDTO>();
        hotels = hotelRepository.getHotelsWithBudget(pays, ville, budget);

        return hotels;
    }

    public HotelDTO getHotelById(Long idHotel) {
        HotelDTO hotel = hotelRepository.getHotelById(idHotel);
        List<Equipement> equipements = hotelRepository.getEquipementsByIdHotel(idHotel);
        List<OptionDTO> options = hotelRepository.getOptionsByIdHotel(idHotel);
        hotel.setEquipements(equipements);
        hotel.setOptions(options);
        return hotel;
    }
}
