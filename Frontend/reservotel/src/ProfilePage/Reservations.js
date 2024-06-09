import React, { useEffect, useState } from 'react';
import { Container, Typography, Card, CardContent, Grid, Box, Pagination, CardMedia } from '@mui/material';
import { apiClient, fetchCsrfToken } from '../API/api';

const Reservations = () => {
  const [reservations, setReservations] = useState([]);
  const [loading, setLoading] = useState(false);
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [userDetails, setUserDetails] = useState({});

  const itemsPerPage = 5;

  useEffect(() => {
    const getCurrentUser = async () => {
      try {
        const response = await apiClient.get('/auth/getuser');
        setUserDetails({
          nom: response.data.nom,
          prenom: response.data.prenom,
          email: response.data.email,
          dateNaissance: response.data.dateNaissance,
          numeroTelephone: response.data.numeroTelephone,
          pointsFidelite: response.data.pointsFidelite,
          role: response.data.role,
          idClient: response.data.idClient
        });
        console.log("User details set:", response.data);
      } catch (error) {
        console.error('Error during user request:', error);
      }
    };
    getCurrentUser();
  }, []);

  useEffect(() => {
    if (!userDetails.email) {
      return;
    }

    const fetchReservations = async () => {
      setLoading(true);
      try {
        await fetchCsrfToken();
        const response = await apiClient.get('/profil/reservations', {
          params: { email: userDetails.email, page: page, limit: itemsPerPage }
        });
        setReservations(response.data);
        if (response.data.length > 0) {
          setTotalPages(Math.ceil(response.data[0].totalReservations / itemsPerPage));
        }
      } catch (error) {
        console.error('Error fetching reservations:', error);
      } finally {
        setLoading(false);
      }
    };

    fetchReservations();
  }, [page, userDetails.email]);

  if (loading) {
    return <Typography>Loading...</Typography>;
  }

  return (
    <Container
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        pt: { xs: 14, sm: 20 },
        pb: { xs: 8, sm: 12 },
      }}
    >
      <Typography variant="h4" gutterBottom>
        Mes réservations
      </Typography>
      
      <Grid container spacing={4}>
        {reservations.map((reservation) => (
          <Grid item xs={12} key={reservation.idReservation}>
            <Card>
              {reservation.detailsReservations[0] && reservation.detailsReservations[0].chambre.hotel.photoHotel && (
                <CardMedia
                  component="img"
                  height="140"
                  image={reservation.detailsReservations[0].chambre.hotel.photoHotel}
                  alt="Hotel image"
                />
              )}
              <CardContent>
                <Typography variant="h6">Hotel: {reservation.detailsReservations[0].chambre.hotel.nomHotel}</Typography>
                <Typography>Date d'arrivée: {reservation.dateArrive}</Typography>
                <Typography>Date de départ: {reservation.dateDepart}</Typography>
                <Typography>Status: {reservation.statutReservation}</Typography>
                <Typography>Prix total: {reservation.prixTotalReservation} €</Typography>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
      
      <Box sx={{ display: 'flex', justifyContent: 'center', marginTop: 4 }}>
        <Pagination
          count={totalPages}
          page={page}
          onChange={(event, value) => setPage(value)}
        />
      </Box>
    </Container>
  );
};

export default Reservations;