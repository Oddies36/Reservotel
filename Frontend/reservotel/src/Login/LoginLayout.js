import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import CssBaseline from '@mui/material/CssBaseline';
import { alpha, useTheme } from '@mui/material';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import backgroundImage from '../Images/hotel.jpg';
import logo from '../Images/logo.png';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { Link as RouterLink} from 'react-router-dom';

const defaultTheme = createTheme();

export default function LoginLayout({ children, title }) {
  const theme = useTheme();
  return (
    <ThemeProvider theme={defaultTheme}>
      <Grid container component="main" sx={{ height: '100vh' }}>
        <CssBaseline />
        <Grid
          item
          xs={false}
          sm={4}
          md={7}
          sx={{
            backgroundImage: `url(${backgroundImage})`,
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light' ? t.palette.grey[50] : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
        <Grid
        item
        xs={12}
        sm={8}
        md={5}
        component={Paper}
        elevation={6}
        square
        sx={(theme) => ({
          width: '100%',
          backgroundImage:
            theme.palette.mode === 'light'
              ? 'linear-gradient(180deg, #CEE5FD, #FFF)'
              : `linear-gradient(#02294F, ${alpha('#090E10', 0.0)})`,
          backgroundSize: '100% 30%',
          backgroundRepeat: 'no-repeat',
        })}
        >
          <Box
            sx={{
              my: 4,
              mx: 4,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
            <RouterLink to="/login">
              <img src={logo} alt="logo" style={{ cursor: 'pointer' }} />
            </RouterLink>
            <Avatar sx={{ m: 1, bgcolor: '#345995' }}>
              <LockOutlinedIcon sx={{ color: theme.palette.primary.black }}/>
            </Avatar>
            <Typography component="h1" variant="h5">
              {title}
            </Typography>
            {children}
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
}