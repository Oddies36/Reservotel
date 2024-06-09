import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { apiClient } from '../API/api';
import LoginLayout from './LoginLayout';
import { Link as DomLink, useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import { login } from '../API/api';
import { fetchCsrfToken } from '../API/api';

export default function Login() {
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const email = data.get('email');
    const password = data.get('password');

    try {
      await fetchCsrfToken();
      const response = await apiClient.post('/auth/login', { email, password });

      if (response.data === 'ok') {
        Swal.fire({
          title: 'Connexion réussie',
          text: 'Vous allez être redirigé vers la page d\'accueil',
          icon: 'success',
          confirmButtonText: 'OK'
        }).then(() => {
          navigate('/home');
        });
      } 
    } catch (error) {
      if (error.response) {
        const errorMessage = error.response.data;
        if (errorMessage === 'emailNotExist') {
          Swal.fire({
            title: 'Erreur',
            text: 'L\'adresse mail n\'existe pas',
            icon: 'error',
            confirmButtonText: 'OK'
          });
        } else if (errorMessage === 'passwordIncorrect') {
          Swal.fire({
            title: 'Erreur',
            text: 'Le mot de passe est incorrect',
            icon: 'error',
            confirmButtonText: 'OK'
          });
        } else {
          Swal.fire({
            title: 'Erreur',
            text: 'Une erreur est survenue lors de la connexion',
            icon: 'error',
            confirmButtonText: 'OK'
          });
        }
      } else {
        console.error('Error during login request:', error);
        Swal.fire({
          title: 'Erreur',
          text: 'Une erreur est survenue lors de la connexion',
          icon: 'error',
          confirmButtonText: 'OK'
        });
      }
    }
  };

  return (
    <LoginLayout title="Connexion">
      <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
        <TextField
          sx={{ '& .MuiOutlinedInput-root': { borderRadius: '10px' }}}
          margin="normal"
          required
          fullWidth
          id="email"
          label="Adresse mail"
          name="email"
          autoComplete="email"
          autoFocus
        />
        <TextField
          sx={{ '& .MuiOutlinedInput-root': { borderRadius: '10px' }}}
          margin="normal"
          required
          fullWidth
          name="password"
          label="Mot de passe"
          type="password"
          id="password"
          autoComplete="current-password"
        />
        <Button
          type="submit"
          fullWidth
          variant="contained"
          sx={{ mt: 3, mb: 2 }}
        >
          Connexion
        </Button>
        <Grid container>
          <Grid item xs>
            <DomLink to="/mot-de-passe-oublie">
              Mot de passe oublié ?
            </DomLink>
          </Grid>
          <Grid item>
            <DomLink to="/register">
              Créer un compte
            </DomLink>
          </Grid>
        </Grid>
      </Box>
    </LoginLayout>
  );
}