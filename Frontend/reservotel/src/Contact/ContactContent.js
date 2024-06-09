import React, { useState } from 'react';
import { Container, TextField, Button, Typography, Box } from '@mui/material';

export default function ContactContent() {
  const [nom, setNom] = useState('');
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);
  const [error, setError] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();
    setLoading(true);
    setSuccess(false);
    setError('');


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
      <Typography variant="h4" gutterBottom>
        Contactez nous
      </Typography>
      <Box
        component="form"
        onSubmit={handleSubmit}
        sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', width: '100%', maxWidth: 500 }}
      >
        <TextField
          label="Nom"
          variant="outlined"
          fullWidth
          margin="normal"
          value={nom}
          onChange={(e) => setNom(e.target.value)}
          required
        />
        <TextField
          label="Email"
          variant="outlined"
          fullWidth
          margin="normal"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <TextField
          label="Message"
          variant="outlined"
          fullWidth
          margin="normal"
          multiline
          rows={4}
          value={message}
          onChange={(e) => setMessage(e.target.value)}
          required
        />
        {success && <Typography color="success.main">Votre message a bien été envoyé!</Typography>}
        {error && <Typography color="error.main">{error}</Typography>}
        <Button
          type="submit"
          variant="contained"
          color="primary"
          sx={{ mt: 3 }}
          disabled={loading}
        >
          {loading ? 'En cours...' : 'Envoyer'}
        </Button>
      </Box>
    </Container>
  );
}