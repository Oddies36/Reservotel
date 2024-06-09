import React, { useEffect, useState } from 'react';
import { useParams, useLocation, useNavigate } from 'react-router-dom';
import { Checkbox, Dialog, Rating, Container, Card, CardContent, CardMedia, Typography, List, ListItem, ListItemText, CircularProgress, Box, Grid, Button, SwipeableDrawer, IconButton, DialogContent, FormGroup, FormControlLabel } from '@mui/material';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { apiClient } from '../API/api';
import Swal from 'sweetalert2';
import { fetchCsrfToken } from '../API/api';

export default function HotelSelectContent() {
  const location = useLocation();
  const navigate = useNavigate();
  const searchParams = new URLSearchParams(location.search);

  const { idHotel } = useParams();
  const nombrePersonnesURL = searchParams.get('nombrePersonnes');
  const dateArriveURL = searchParams.get('dateArrive');
  const dateDepartURL = searchParams.get('dateDepart');

  const [selectedHotel, setSelectedHotel] = useState(null);
  const [loading, setLoading] = useState(true);

  const [chambres, setChambres] = useState([]);
  const [nombreChambresDispo, setNombreChambresDispo] = useState([]);

  const [selectedChambreType, setSelectedChambreType] = useState('');
  const [selectedChambrePrix, setSelectedChambrePrix] = useState(0);

  const [pannier, setPannier] = useState([]);
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const [dialogOpen, setDialogOpen] = useState(false);

  const [selectedOptions, setSelectedOptions] = useState([]);
  const [selectedOptionsName, setSelectedOptionsName] = useState([]);

  const [prixTotalOptions, setPrixTotalOptions] = useState(0);
  const [prixTotal, setPrixTotal] = useState(0);

  const [userDetails, setUserDetails] = useState({
    nom: '',
    prenom: '',
    email: '',
    dateNaissance: '',
    numeroTelephone: '',
    pointsFidelite: 0,
    role: ''
  });

  const [choixPointFidelite, setChoixPointFidelite] = useState(false);

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
          role: response.data.role
        });
      } catch (error) {
        console.error('Error during user request:', error);
      }
    };
    getCurrentUser();
  }, []);

  useEffect(() => {
    const fetchHotelDetails = async () => {
      try {
        const response = await apiClient.get(`/hotel/${idHotel}`);
        setSelectedHotel(response.data);
      } catch (error) {
        console.error('Error during hotel details request:', error);
      } finally {
        setLoading(false);
      }
    };
    fetchHotelDetails();
  }, [idHotel]);

  useEffect(() => {
    const fetchChambres = async () => {
      try {
        const response1 = await apiClient.get(`/chambre/${idHotel}/dispo`);
        const response2 = await apiClient.get(`/chambre/${idHotel}/total`);

        const availableChambres = response1.data;
        const allChambres = response2.data;

        const nombreChambres = allChambres.map(chambre => {
          const availableChambre = availableChambres.find(ac => ac.nombrePersonnes === chambre.nombrePersonnes);
          return {
            ...chambre,
            compte: availableChambre ? availableChambre.compte : 0
          };
        });

        setNombreChambresDispo(nombreChambres);
        setChambres(allChambres);
      } catch (error) {
        console.error('Error during chambres request:', error);
      } finally {
        setLoading(false);
      }
    };
    fetchChambres();
  }, [idHotel]);

  useEffect(() => {
    const calculateTotalPrice = () => {
      const total = pannier.reduce((acc, item) => acc + item.prixTotal, 0);
      setPrixTotal(total);
    };
    calculateTotalPrice();
  }, [pannier]);

  const toggleDrawer = (open) => (event) => {
    if (event && event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
      return;
    }
    setIsDrawerOpen(open);
  };

  const openOptions = () => {
    setDialogOpen(true);
  };

  const updateSelectedOptions = (idOption, prixOption) => {
    setSelectedOptions(prevSelectedOptions => {
      const isOptionSelected = prevSelectedOptions.includes(idOption);
      if (isOptionSelected) {
        setPrixTotalOptions(prevPrixTotalOptions => prevPrixTotalOptions - prixOption);
        return prevSelectedOptions.filter(option => option !== idOption);
      } else {
        setPrixTotalOptions(prevPrixTotalOptions => prevPrixTotalOptions + prixOption);
        return [...prevSelectedOptions, idOption];
      }
    });
  };

  const updateSelectedOptionsName = (nomOption) => {
    setSelectedOptionsName(prevSelectedOptionsName => prevSelectedOptionsName.includes(nomOption)
      ? prevSelectedOptionsName.filter(option => option !== nomOption)
      : [...prevSelectedOptionsName, nomOption]
    );
  };

  const ajouterAuPannier = () => {
    const selection = {
      nombrePersonnes: selectedChambreType,
      options: selectedOptions,
      nomOptions: selectedOptionsName,
      prixBase: selectedChambrePrix,
      prixTotalOptions: prixTotalOptions,
      prixTotal: prixTotalOptions + selectedChambrePrix
    };

    const key = selectedChambreType;

    setPannier(prevPannier => [...prevPannier, selection]);

    setNombreChambresDispo(prevNombreChambresDispo => {
      const updatedChambres = prevNombreChambresDispo.map(chambre => {
        if (chambre.nombrePersonnes === key) {
          return {
            ...chambre,
            compte: chambre.compte - 1
          };
        }
        return chambre;
      });
      return updatedChambres;
    });

    setDialogOpen(false);
    setSelectedOptions([]);
    setSelectedOptionsName([]);
    setSelectedChambrePrix(0);
    setPrixTotalOptions(0);
    setIsDrawerOpen(true);
  };

  const checkNombrePersonnes = () => {
    const nombreReserve = pannier.reduce((total, item) => total + item.nombrePersonnes, 0);

    if (nombreReserve < nombrePersonnesURL) {
      Swal.fire({
        title: "Attention",
        text: "Vous n'avez pas assez de chambres pour le nombre de personnes que vous avez introduit",
        icon: "warning"
      });
    }
    else {
      utiliserPointsFidelite();
    }
  };

  const utiliserPointsFidelite = () => {
    const pointsActuels = userDetails.pointsFidelite;
    const reductionPossible = (pointsActuels * 0.01).toFixed(2);

    Swal.fire({
      title: "Utilisez vos points!",
      text: `Voulez vous utiliser vos points? Vous avez actuellement ${pointsActuels} points, ce qui vous donne une réduction de ${reductionPossible}€`,
      icon: "question",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Oui",
      cancelButtonText: "Non"
    }).then((result) => {
      if (result.isConfirmed) {
        setChoixPointFidelite(true);
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        validerReservation();
      }
    });
  };

  useEffect(() => {
    if (choixPointFidelite === true) {
      validerReservation();
    }
  }, [choixPointFidelite]);

  const validerReservation = () => {
    sendReservation();
    Swal.fire({
      title: "Succès",
      text: "Votre réservation a été effectuée avec succès",
      icon: "success"
    }).then(() => {
      navigate('/home');
    });
  };

  const sendReservation = async () => {
    const userEmail = userDetails.email;
    try {
      await fetchCsrfToken();
      const response = await apiClient.post('/reservation/create', { pannier, userEmail, dateArriveURL, dateDepartURL, idHotel, choixPointFidelite, nombrePersonnesURL });
      const payload = { pannier, userEmail, dateArriveURL, dateDepartURL, idHotel, choixPointFidelite };
      console.log('Payload:', JSON.stringify(payload, null, 2)); // Log the payload
      return response;
    } catch (error) {
      console.error('Error during reservation request:', error);
    }
  };

  if (loading) {
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
        <CircularProgress />
      </Container>
    );
  }

  if (!selectedHotel) {
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
        <Typography variant="h6" component="div" gutterBottom>
          Loading...
        </Typography>
      </Container>
    );
  }

  return (
    <Container
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        pt: { xs: 10, sm: 16 },
        pb: { xs: 8, sm: 12 },
      }}
    >
      <Typography
        textAlign="center"
        component="span"
        variant="h1"
        sx={{
          fontSize: 'clamp(3rem, 10vw, 4rem)',
          color: (theme) =>
            theme.palette.mode = 'primary.main',
          mb: 3,
        }}
      >
        {selectedHotel.nomHotel}
      </Typography>
      <Box sx={{ display: 'flex', flexDirection: { xs: 'column', md: 'row' }, gap: 2, width: '100%', maxWidth: 1400 }}>
        <Card sx={{ flex: 1 }}>
          <CardMedia
            component="img"
            alt={selectedHotel.nomHotel}
            height="300"
            image={selectedHotel.photoHotel}
            title={selectedHotel.nomHotel}
          />
          <CardContent>
            <Rating name="read-only" value={selectedHotel.etoiles} readOnly />
            <Typography variant="body1" color="text.secondary" gutterBottom>
              Description: {selectedHotel.descriptionHotel}
            </Typography>
            <Typography variant="body1" color="text.secondary" gutterBottom>
              Prix minimum d'une chambre: {selectedHotel.prixChambreMinimum}€
            </Typography>
            <Typography variant="body1" color="text.secondary" gutterBottom>
              Contact téléphone: {selectedHotel.contactTelephone}
            </Typography>
            <Typography variant="body1" color="text.secondary" gutterBottom>
              Contact Email: {selectedHotel.contactEmail}
            </Typography>
            <Typography variant="body1" color="text.secondary" gutterBottom>
              Adresse: {`${selectedHotel.adresse.rue}, ${selectedHotel.adresse.numero}, ${selectedHotel.adresse.boite}, ${selectedHotel.adresse.ville}, ${selectedHotel.adresse.codePostal}, ${selectedHotel.adresse.pays}`}
            </Typography>
          </CardContent>
        </Card>

        <Card sx={{ flex: 1 }}>
          <CardContent>
            <Typography variant="h5" component="div" gutterBottom>
              Equipements
            </Typography>
            <List sx={{ width: '100%', bgcolor: 'background.paper' }}>
              {selectedHotel.equipements.map(equipement => (
                <ListItem key={equipement.idEquipement}>
                  <ListItemText primary={equipement.nomEquipement} secondary={equipement.descriptionEquipement} />
                </ListItem>
              ))}
            </List>
          </CardContent>
        </Card>
      </Box>

      <Typography variant="h5" component="div" gutterBottom sx={{ mt: 4 }}>
        Chambres
      </Typography>
      <Grid container spacing={2}>
        {nombreChambresDispo.map((chambre, index) => (
          <Grid item xs={12} sm={6} md={4} key={index}>
            <Card>
              <CardMedia
                component="img"
                alt={`Room for ${chambre.nombrePersonnes} people`}
                height="200"
                image={chambre.photo}
                title={`Room for ${chambre.nombrePersonnes} people`}
              />
              <CardContent>
                <Typography variant="h5" component="div">
                  Chambre {chambre.nombrePersonnes} personne(s)
                </Typography>
                <Typography variant="body1" color="text.secondary" gutterBottom>
                  Prix sans options: {chambre.prixBase}€
                </Typography>
                <Typography variant="body1" color="text.secondary" gutterBottom>
                  Nombre disponible: {chambre.compte}
                </Typography>
                <Box sx={{ display: 'flex', justifyContent: 'center', mt: 2 }}>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={() => {
                      openOptions();
                      setSelectedChambreType(chambre.nombrePersonnes);
                      setSelectedChambrePrix(chambre.prixBase);
                    }}
                    disabled={chambre.compte === 0}
                  >
                    {chambre.compte === 0 ? 'Indisponible' : 'Choisir les options'}
                  </Button>
                </Box>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>

      <Dialog open={dialogOpen}>
        <DialogContent>
          <Typography variant="h5" component="div" textAlign={'center'} marginBottom={2}>
            Options
          </Typography>
          <Typography>
            Chambre {selectedChambreType} personne(s)
          </Typography>

          <Grid container>
            {selectedHotel.options.map(option => (
              <Grid item xs={12} sm={6} key={option.idOption}>
                <FormGroup>
                  <FormControlLabel
                    control={
                      <Checkbox
                        checked={selectedOptions.includes(option.idOption)}
                        onChange={() => { updateSelectedOptions(option.idOption, option.prixOption); updateSelectedOptionsName(option.nomOption); }}
                      />
                    }
                    label={`${option.nomOption} - ${option.prixOption}€`}
                  />
                </FormGroup>
              </Grid>
            ))}
          </Grid>

          <Grid container padding={2}>
            <Grid item xs={6} sx={{ textAlign: 'left' }}>
              <Button variant="contained" color="primary" onClick={ajouterAuPannier}>
                Ajouter à la réservation
              </Button>
            </Grid>
            <Grid item xs={6} sx={{ textAlign: 'center' }}>
              <Button variant="contained" color="primary" onClick={() => { setDialogOpen(false); setSelectedOptions([]); setSelectedOptionsName([]); }}>
                Annuler
              </Button>
            </Grid>
          </Grid>
        </DialogContent>
      </Dialog>

      <SwipeableDrawer
        anchor="right"
        open={isDrawerOpen}
        onClose={toggleDrawer(false)}
        onOpen={toggleDrawer(true)}
      >
        <Box
          sx={{
            width: 300,
            p: 2
          }}
          role="presentation"
          onClick={toggleDrawer(false)}
          onKeyDown={toggleDrawer(false)}
        >
          <Typography variant="h6" gutterBottom>
            Réservation
          </Typography>
          <List>
            {pannier.map((item, index) => (
              <ListItem key={index}>
                <ListItemText
                  primary={`Chambre ${item.nombrePersonnes} personne(s)`}
                  secondary={
                    <>
                      <Typography component="span" variant="body2">
                        Options: {item.nomOptions.join(', ')}
                      </Typography>
                      <Typography component="span" variant="body2" display="block">
                        Prix de la chambre: {item.prixBase}€
                      </Typography>
                      <Typography component="span" variant="body2" display="block">
                        Prix des options: {item.prixTotalOptions}€
                      </Typography>
                      <Typography component="span" variant="body2" display="block">
                        Prix total: {item.prixTotal}€
                      </Typography>
                    </>
                  } />
              </ListItem>
            ))}
          </List>
          <Typography variant="h6" gutterBottom>
            Prix total: {prixTotal}€
          </Typography>
          <Button variant="contained" color="primary" onClick={checkNombrePersonnes}>
            Payer
          </Button>
        </Box>
      </SwipeableDrawer>

      <IconButton
        color="primary"
        sx={{
          position: 'fixed',
          bottom: 16,
          right: 16,
          zIndex: 1300,
        }}
        onClick={toggleDrawer(true)}
      >
        <ShoppingCartIcon sx={{ fontSize: 40 }} />
      </IconButton>
    </Container>
  );
}