package be.reservotel.reservotel.DTO;

import be.reservotel.reservotel.Model.Adresse;
import be.reservotel.reservotel.Model.Client;

public class AddrClientDTO {
    
    private Client client;
    private Adresse adresse;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
