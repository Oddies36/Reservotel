import * as React from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { Card, CardContent, CardMedia, Typography, CardActionArea, Grid, Container, Pagination, Rating } from '@mui/material';
import { apiClient } from '../API/api';
import { Link } from 'react-router-dom';




export default function HotelSearchContent() {

  const [selectedCountry, setSelectedCountry] = useState('');
  const [selectedCity, setSelectedCity] = useState('');
  const [selectedDateArrive, setSelectedDateArrive] = useState('');
  const [selectedDateDepart, setSelectedDateDepart] = useState('');
  const [selectedNombrePersonnes, setSelectedNombrePersonnes] = useState('');
  const [selectedBudget, setSelectedBudget] = useState('');

  const [loading, setLoading] = useState(true);

  const [hotels, setHotels] = useState([]);

  const navigate = useNavigate();
  const location = useLocation();

  //Pour quand un utilisateur essaye d'aller directement à la page de recherche d'hotel sans introduire de pays ou de ville
  useEffect(() => {
    const queryParams = new URLSearchParams(location.search);
    const country = queryParams.get('country');
    const city = queryParams.get('city');
    const dateArrive = queryParams.get('dateArrive');
    const dateDepart = queryParams.get('dateDepart');
    const nombrePersonnes = queryParams.get('nombrePersonnes');
    const budget = queryParams.get('budget');

    if (!country || !city) {
      navigate('/home');

    }
    else {
      setSelectedCountry(country);
      setSelectedCity(city);
      setLoading(false);
      setSelectedDateArrive(dateArrive);
      setSelectedDateDepart(dateDepart);
      setSelectedNombrePersonnes(nombrePersonnes);
      setSelectedBudget(budget);
    }

  }, [location.search, navigate]);




  useEffect(() => {
    const fetchHotels = async () => {
      try {
        if (!selectedBudget) {
          const response = await apiClient.get(`/hotel/${selectedCountry}/${selectedCity}`);
          setHotels(response.data)
        }
        else {
          const response = await apiClient.get(`/hotel/${selectedCountry}/${selectedCity}/${selectedBudget}`);
          setHotels(response.data)
        }

      } catch (error) {
        console.error('Erreur de données: ', error);
      }
    };

    if (selectedCity && selectedCountry) {
      fetchHotels();
    }
  }, [selectedCity, selectedCountry]);




  if (loading) {
    return <Typography>Loading...</Typography>;
  }

  const handleHotelClicked = (id) => {
    navigate(`/hotels/${selectedCountry}/${selectedCity}/${id}?dateArrive=${selectedDateArrive}&dateDepart=${selectedDateDepart}&nombrePersonnes=${selectedNombrePersonnes}&budget=${selectedBudget}`)
  };








  return (
    <Container
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        pt: { xs: 14, sm: 20 },
        pb: { xs: 8, sm: 12 },
      }}>
      <Typography variant="h4" gutterBottom marginBottom={5}>
        Hotels à {selectedCity}, {selectedCountry}
      </Typography>
      <Grid container spacing={2}>
        {hotels.map((hotel) => (
          <Grid item xs={12} sm={6} md={4} key={hotel.idHotel}>
            <Card>
              <CardActionArea onClick={() => handleHotelClicked(hotel.idHotel)}>
                <CardMedia
                  component="img"
                  height="200"
                  image={hotel.photoHotel}
                  alt={hotel.nomHotel}
                />
                <CardContent>
                  <Typography variant="h5" component="div">
                    {hotel.nomHotel}
                  </Typography>
                  <Rating name="read-only" value={hotel.etoiles} readOnly />
                  <Typography variant="body2" color="text.secondary">
                    {hotel.descriptionHotel}
                  </Typography>
                  <Typography variant="body1" color="text.primary">
                    A partir de {hotel.prixChambreMinimum}€ par nuit
                  </Typography>
                </CardContent>
              </CardActionArea>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Container>
  );
}