import * as React from 'react';

import Box from '@mui/material/Box';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import MenuItem from '@mui/material/MenuItem';
import { Link as DomLink} from 'react-router-dom';

function NavBar() {
  
  return (
    <div>
      <AppBar
        position="fixed"
        sx={{
          boxShadow: 0,
          bgcolor: 'transparent',
          backgroundImage: 'none',
          mt: 2,
        }}
      >
        <Container maxWidth="lg">
          <Toolbar
            variant="regular"
            sx={(theme) => ({
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'space-between',
              flexShrink: 0,
              borderRadius: '999px',
              bgcolor:
                theme.palette.mode === 'light'
                  ? 'rgba(255, 255, 255, 0.4)'
                  : 'rgba(0, 0, 0, 0.4)',
              backdropFilter: 'blur(24px)',
              maxHeight: 40,
              border: '1px solid',
              borderColor: 'divider',
              boxShadow:
                theme.palette.mode === 'dark'
                  ? `0 0 1px rgba(85, 166, 246, 0.1), 1px 1.5px 2px -1px rgba(85, 166, 246, 0.15), 4px 4px 12px -2.5px rgba(85, 166, 246, 0.15)`
                  : '0 0 1px rgba(2, 31, 59, 0.7), 1px 1.5px 2px -1px rgba(2, 31, 59, 0.65), 4px 4px 12px -2.5px rgba(2, 31, 59, 0.65)',
            })}
          >
            <Box
              sx={{
                flexGrow: 1,
                display: 'flex',
                alignItems: 'center',
                ml: '-18px',
                px: 0,
              }}
            >
              <MenuItem
                component={DomLink}
                to="/home"
                sx={{ py: '6px', px: '12px' }}
              >
                <Typography variant="button" color="text.primary">
                  Accueil
                </Typography>
              </MenuItem>
              <MenuItem
                component={DomLink}
                to="/home"
                sx={{ py: '6px', px: '12px' }}
              >
                <Typography variant="button" color="text.primary">
                  Mon profil
                </Typography>
              </MenuItem>
              <MenuItem
                component={DomLink}
                to="/home"
                sx={{ py: '6px', px: '12px' }}
              >
                <Typography variant="button" color="text.primary">
                  Contact
                </Typography>
              </MenuItem>
            </Box>
            <Box sx={{ ml: 'auto' }}>
              <MenuItem
                component={DomLink}
                to="/login"
                sx={{ py: '6px', px: '12px' }}
              >
                <Typography variant="button" color="text.primary">
                  Déconnexion
                </Typography>
              </MenuItem>
            </Box>
          </Toolbar>
        </Container>
      </AppBar>
    </div>
  );
}

export default NavBar;