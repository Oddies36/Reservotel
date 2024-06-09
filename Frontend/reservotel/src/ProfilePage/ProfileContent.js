import * as React from 'react';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { Card, CardContent, CardMedia, Typography, CardActionArea, Grid, Container, Box } from '@mui/material';
import { apiClient } from '../API/api';
import Information from '../Images/Information.png';
import reservations from '../Images/reservations.jpg';

export default function ProfileContent() {
  const [loading, setLoading] = useState(true);
  const [profile, setProfile] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    const getCurrentUser = async () => {
      try {
        const response = await apiClient.get('/auth/getuser');
        setProfile({
          nom: response.data.nom,
          prenom: response.data.prenom,
          email: response.data.email,
          dateNaissance: response.data.dateNaissance,
          numeroTelephone: response.data.numeroTelephone,
          pointsFidelite: response.data.pointsFidelite,
          role: response.data.role,
        });
      } catch (error) {
        console.error('Error during user request:', error);
      } finally {
        setLoading(false);
      }
    };

    getCurrentUser();
  }, []);

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
      <Typography variant="h4" gutterBottom marginBottom={5}>
        Mon profil
      </Typography>
      
      <Box sx={{ display: 'flex', justifyContent: 'space-around', width: '100%', marginBottom: 5}}>
        <Card sx={{ maxWidth: 300 }}>
          <CardActionArea onClick={() => navigate('/profile/reservations')}>
          <CardMedia
              component="img"
              height="200"
              image={reservations}
              alt="Mes réservations"
            />
            <CardContent>
              <Typography variant="h5" component="div">
                Mes réservations
              </Typography>
            </CardContent>
          </CardActionArea>
        </Card>
        
        <Card sx={{ maxWidth: 300 }}>
          <CardActionArea onClick={() => navigate('/profile/informations')}>
            <CardMedia
              component="img"
              height="200"
              image={Information}
              alt="Mes informations"
            />
            <CardContent>
              <Typography variant="h5" component="div">
                Mes informations
              </Typography>
            </CardContent>
          </CardActionArea>
        </Card>
      </Box>
    </Container>
  );
}