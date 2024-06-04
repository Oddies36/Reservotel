import * as React from 'react';

import { ThemeProvider, createTheme } from '@mui/material/styles';
import { Box, alpha } from '@mui/material';
import NavBar from '../Components/NavBar';
import Footer from '../Components/Footer';
import HotelSearchContent from './HotelSearchContent';


export default function HotelSearch() {
  const [mode] = React.useState('light');
  const defaultTheme = createTheme({ palette: { mode } });


  return (
    <ThemeProvider theme={defaultTheme}>
      <NavBar />
      <Box
        id="gradientBackground"
        sx={(theme) => ({
          width: '100%',
          height: '300px',
          backgroundImage:
            theme.palette.mode === 'light'
              ? 'linear-gradient(180deg, #CEE5FD, #FFF)'
              : `linear-gradient(#02294F, ${alpha('#090E10', 0.0)})`,
          backgroundSize: 'cover',
          backgroundRepeat: 'no-repeat',
          backgroundPosition: 'top',
        })}
      />
      <Box
        id="contentWrapper"
        sx={{
          width: '100%',
          marginTop: '-400px',
          paddingTop: '150px',
        }}
      >
        <HotelSearchContent />
      </Box>
      <Footer />
    </ThemeProvider>
  );
}