import * as React from 'react';
import { useEffect, useState } from 'react';
import { Box, Container, Stack, Typography, Grid, TextField, FormControl, InputLabel, Select, MenuItem, Button, alpha } from '@mui/material';
import Slider from 'react-slick';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import logo1 from '../Images/homeimage1.jpg';
import logo2 from '../Images/homeimage2.jpg';
import logo3 from '../Images/homeimage3.jpg';
import { apiClient } from '../API/api';
import { useNavigate } from 'react-router-dom';
import { LocalizationProvider, DatePicker } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import dayjs from 'dayjs';
import * as Yup from 'yup';
import { useFormik } from 'formik';

const validationSchema = Yup.object({
  nombrePersonnes: Yup.number().required('Introduisez le nombre de personnes')
    .max(10, 'Le nombre de personnes ne peut pas dépasser 10')
    .positive('Le nombre de personnes doit être positif')
    .integer('Mauvais format'),
});

export default function MainContent() {
  const dateToday = dayjs();

  const navigate = useNavigate();

  const [hotelCountries, setCountries] = useState([]);
  const [hotelCities, setCities] = useState([]);

  const images = [logo1, logo2, logo3];

  const formik = useFormik({
    initialValues: {
      nombrePersonnes: '',
      budget: '',
      pays: '',
      ville: '',
      dateArrive: dateToday.add(1, 'day'),
      dateDepart: dateToday.add(2, 'day'),
    },

    validationSchema: validationSchema,
    onSubmit: async (values) => {
      const recherche = {
        nombrePersonnes: values.nombrePersonnes,
        budget: values.budget,
        pays: values.pays,
        ville: values.ville,
        dateArrive: dayjs(values.dateArrive).format('YYYY-MM-DD'),
        dateDepart: dayjs(values.dateDepart).format('YYYY-MM-DD'),
      };

      navigate(`/hotels?country=${formik.values.pays}&city=${formik.values.ville}&dateArrive=${recherche.dateArrive}&dateDepart=${recherche.dateDepart}&nombrePersonnes=${formik.values.nombrePersonnes}&budget=${formik.values.budget}`)
    }

  });

  useEffect(() => {
    const fetchCountries = async () => {
      try {
        const response = await apiClient.get('/hotel/countries');
        setCountries(response.data);

      } catch (error) {
        console.error('Erreur de données: ', error);
      }
    }

    fetchCountries();
  }, []);

  useEffect(() => {
    if (formik.values.pays) {
      const fetchCities = async () => {
        try {
          const response = await apiClient.get(`/hotel/${formik.values.pays}/villes`);
          setCities(response.data);
        } catch (error) {
          console.error('Erreur de données: ', error);
        }
      };

      fetchCities();
    }
  }, [formik.values.pays]);

  const handleDateArriveChange = (value) => {
    formik.setFieldValue('dateArrive', value);
    formik.setFieldValue('dateDepart', value.add(1, 'day'));
  };

  const handleDateDepartChange = (value) => {
    formik.setFieldValue('dateDepart', value);
    
  };


  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
  };

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
      <Stack spacing={2} useFlexGap sx={{ width: { xs: '100%', sm: '70%' } }}>
        <Typography
          textAlign="center"
          component="span"
          variant="h1"
          sx={{
            fontSize: 'clamp(3rem, 10vw, 4rem)',
            color: (theme) =>
              theme.palette.mode === 'light' ? 'primary.main' : 'primary.light',
          }}
        >
          Reservotel
        </Typography>

        <Typography
          textAlign="center"
          color="text.secondary"
          sx={{ alignSelf: 'center', width: { sm: '100%', md: '80%' } }}
        >
          Reservez un hotel en toute facilité.
        </Typography>

        <form onSubmit={formik.handleSubmit}>
          <Box
            id="recherche"
            border={1}
            borderRadius={2}
            boxShadow={2}
            p={2}
            mt={2}
          >
            <Grid container spacing={2}>
              {/*La colonne recherche a gauche*/}
              <Grid item xs={12} md={6}>
                <Grid container spacing={2}>
                  <Grid item xs={12}>
                    <FormControl fullWidth variant="outlined">
                      <InputLabel id="city-select-label">Ville</InputLabel>
                      <Select
                        labelId="city-select-label"
                        id="city-select"
                        name="ville"
                        value={formik.values.ville}
                        onChange={formik.handleChange}
                        label="Ville"
                        required
                      >
                        {hotelCities.map((ville, index) => (
                          <MenuItem key={index} value={ville.ville}>
                            {ville.ville}
                          </MenuItem>
                        ))}
                      </Select>
                    </FormControl>
                  </Grid>
                  <Grid item xs={12}>
                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                      <DatePicker
                        label="Date d'arrivée"
                        name="dateArrive"
                        slotProps={{ textField: { fullWidth: true } }} 
                        minDate={dateToday}
                        onChange={handleDateArriveChange}
                        />
                    </LocalizationProvider>
                  </Grid>
                  <Grid item xs={12}>
                  <LocalizationProvider dateAdapter={AdapterDayjs}>
                      <DatePicker
                        label="Date de départ"
                        name="dateDepart"
                        slotProps={{ textField: { fullWidth: true } }}
                        minDate={formik.values.dateArrive.add(1, 'day')}
                        onChange={handleDateDepartChange}
                      />
                    </LocalizationProvider>
                  </Grid>
                </Grid>
              </Grid>

              {/*La colonne recherche a droite*/}
              <Grid item xs={12} md={6}>
                <Grid container spacing={2}>
                  <Grid item xs={12}>
                    <FormControl fullWidth variant="outlined">
                      <InputLabel id="country-select-label">Pays</InputLabel>
                      <Select
                        labelId="country-select-label"
                        id="country-select"
                        name="pays"
                        value={formik.values.pays}
                        onChange={formik.handleChange}
                        label="Pays"
                        required
                      >
                        {hotelCountries.map((pays, index) => (
                          <MenuItem key={index} value={pays.pays}>
                            {pays.pays}
                          </MenuItem>
                        ))}
                      </Select>
                    </FormControl>
                  </Grid>
                  <Grid item xs={12}>
                    <TextField
                      fullWidth
                      label="Nombre de personnes"
                      variant="outlined" 
                      required
                      name="nombrePersonnes"
                      onChange={formik.handleChange}
                      />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField
                      fullWidth
                      label="Budget"
                      variant="outlined"
                      name="budget"
                      onChange={formik.handleChange}
                      />
                  </Grid>
                </Grid>
              </Grid>
            </Grid>
            <Box sx={{ display: 'flex', justifyContent: 'center', mt: 2 }}>
              <Button
                type="submit"
                variant="contained"
                color="primary"
                sx={{
                  width: '50%',
                }}
              >
                Chercher
              </Button>
            </Box>
          </Box>
        </form>
      </Stack>
      <Box
        id="image-carousel"
        sx={(theme) => ({
          mt: { xs: 8, sm: 10 },
          alignSelf: 'center',
          height: { xs: 200, sm: 700 },
          width: '100%',
          borderRadius: '10px',
          outline: '1px solid',
          outlineColor:
            theme.palette.mode === 'light'
              ? alpha('#BFCCD9', 0.5)
              : alpha('#9CCCFC', 0.1),
          boxShadow:
            theme.palette.mode === 'light'
              ? `0 0 12px 8px ${alpha('#9CCCFC', 0.2)}`
              : `0 0 24px 12px ${alpha('#033363', 0.2)}`,
        })}
      >
        <Slider {...settings}>
          {images.map((image, index) => (
            <div key={index}>
              <Box
                component="img"
                src={image}
                alt={`Carousel ${index}`}
                sx={{
                  height: '100%',
                  width: '100%',
                  objectFit: 'cover',
                  borderRadius: '10px',
                }}
              />
            </div>
          ))}
        </Slider>
      </Box>
    </Container>
  );
}