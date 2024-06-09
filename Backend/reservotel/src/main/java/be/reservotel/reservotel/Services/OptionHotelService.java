package be.reservotel.reservotel.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.reservotel.reservotel.Model.OptionHotel;
import be.reservotel.reservotel.Repository.OptionHotelRepository;

@Service
public class OptionHotelService {

    @Autowired
    private OptionHotelRepository optionHotelRepository;

    public List<OptionHotel> getListeOptionHotel(List<Long> options, Long idHotel) {
        List<OptionHotel> optionHotelList = new ArrayList<>();
        
        for(Long option : options){
            Long idOptionHotel = optionHotelRepository.getIdOptionHotel(idHotel, option);
            OptionHotel optionHotel = new OptionHotel();
            optionHotel = optionHotelRepository.findById(idOptionHotel).get();
            optionHotelList.add(optionHotel);
        }

        return optionHotelList;
    }

    public void insertDetailsReservationOptionHotel(Long idDetailsReservation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertDetailsReservationOptionHotel'");
    }


    //new method
    public Long getIdOptionHotel(Long option, Long idHotel) {
        return optionHotelRepository.getIdOptionHotel(idHotel, option);
    }

    //new method
    public double getPrixOptionHotel(Long idOptionHotel) {
        return optionHotelRepository.getPrixOptionHotel(idOptionHotel);
    }
    
}
