import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import backgroundImage from '../Images/hotel.jpg';
import logo from '../Images/logo.png';
import { apiClient } from '../API/api';
import { fetchCsrfToken } from '../API/api';

const defaultTheme = createTheme();

export default function NumberVerification() {
  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const email = data.get('email');
    const password = data.get('password');

    try{
      await fetchCsrfToken();
      const response = await apiClient.post('/login', {email, password});
      
      if (response.data === 'ok'){
        console.log('Login successful');
      }
      else if (response.data === 'nok'){
        console.error('Login failedez');
      }
      else{
        console.error('Unknown response status');
        console.error(response.data)
      }

    } catch (error) {
      console.error(error);
    }
  };


  return (
    <LoginLayout title="Vérification">
        <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
            <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Derniers 4 chiffres de votre numéro de téléphone"
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
            Envoyer
            </Button>
            <Grid container>
            <Grid item xs>
                <Link href="#" variant="body2">
                Retour à la connexion
                </Link>
            </Grid>
            <Grid item>
                <Link href="#" variant="body2">
                {"Créer un compte"}
                </Link>
            </Grid>
            </Grid>
        </Box>
    </LoginLayout>
  );
}