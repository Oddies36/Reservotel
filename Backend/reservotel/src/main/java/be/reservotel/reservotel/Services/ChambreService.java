package be.reservotel.reservotel.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.reservotel.reservotel.DTO.ChambreDTO;
import be.reservotel.reservotel.Repository.ChambreRepository;


@Service
public class ChambreService {
    

    @Autowired
    private ChambreRepository chambreRepository;

    public List<ChambreDTO> getChambresByIdHotel(Long idHotel) {
        List<ChambreDTO> chambres = chambreRepository.getChambresByIdHotel(idHotel);
        return chambres;
    }

    public List<ChambreDTO> getChambresDispoByIdHotel(Long idHotel) {
        List<ChambreDTO> chambres = chambreRepository.getChambresDispoByIdHotel(idHotel);
        return chambres;
    }


}
