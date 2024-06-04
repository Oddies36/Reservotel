import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { apiClient } from '../API/api';
import LoginLayout from './LoginLayout';
import { Link as DomLink} from 'react-router-dom';

export default function ForgotPassword({ BackToLoginClick }) {
  const handleSubmit = async (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const email = data.get('email');

    try{
      const response = await apiClient.post('/login', {email});
      
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
    <LoginLayout title="Mot de passe oublié">
      <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 1 }}>
        <TextField
          margin="normal"
          required
          fullWidth
          id="email"
          label="Adresse mail"
          name="email"
          autoComplete="email"
          autoFocus
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
            <DomLink to="/login">
              Retour à la connexion
            </DomLink>
          </Grid>
        </Grid>
      </Box>
    </LoginLayout>
  );
}