import * as React from 'react';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { Container, Box, Typography, TextField, Button, Grid, Paper } from '@mui/material';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import { apiClient, fetchCsrfToken } from '../API/api';
import Swal from 'sweetalert2';

const validationSchema = Yup.object({
    email: Yup.string().email('Adresse mail invalide')
        .matches(/^[^\s@]+@[^\s@]+\.[^\s@]+$/, 'Adresse mail invalide')
        .required('L\'adresse mail est requise'),
    numeroTelephone: Yup.string().required('Le numéro de téléphone est requis'),
    password: Yup.string()
        .min(8, 'Le mot de passe doit contenir au moins 8 caractères')
        .matches(/[A-Z]/, 'Le mot de passe doit contenir au moins une majuscule')
        .matches(/[a-z]/, 'Le mot de passe doit contenir au moins une minuscule')
        .matches(/[0-9]/, 'Le mot de passe doit contenir au moins un chiffre')
        .matches(/[!@#+$%^&*]/, 'Le mot de passe doit contenir au moins un caractère spécial')
        .required('Le mot de passe est requis'),
    passwordConfirm: Yup.string()
        .oneOf([Yup.ref('password'), null], 'Les mots de passe ne correspondent pas')
        .required('La confirmation du mot de passe est requise')
});

export default function ProfileContent() {
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();
    const [userDetails, setUserDetails] = useState({});

    useEffect(() => {
        const getCurrentUser = async () => {
            try {
                const response = await apiClient.get('/auth/getuser');
                setUserDetails(response.data);
            } catch (error) {
                console.error('Error during user request:', error);
            }
        };
        getCurrentUser();
    }, []);

    const formik = useFormik({
        initialValues: {
            email: userDetails.email || '',
            numeroTelephone: userDetails.numeroTelephone || '',
            password: '',
            passwordConfirm: '',
        },
        enableReinitialize: true,
        validationSchema: validationSchema,
        onSubmit: async (values) => {
            try {
                const updateDetails = {
                    email: values.email,
                    numeroTelephone: values.numeroTelephone,
                    password: values.password,
                };
                await fetchCsrfToken();
                await apiClient.post('/client/saveclient', updateDetails);
                setUserDetails({ ...userDetails, ...updateDetails });
                Swal.fire({
                    title: 'Informations mises à jour avec succès',
                    icon: 'success',
                    confirmButtonText: 'OK'
                });
            } catch (error) {
                console.error('Erreur de mise à jour', error);
                Swal.fire({
                    title: 'Erreur lors de la mise à jour',
                    icon: 'error',
                    confirmButtonText: 'OK'
                });
            }
        },
    });

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
                Mes informations
            </Typography>
            
            <Paper elevation={3} sx={{ p: 3, width: '100%', maxWidth: 600 }}>
                <Box component="form" noValidate onSubmit={formik.handleSubmit}>
                    <Grid container spacing={2}>
                        <Grid item xs={6}>
                            <Typography variant="body1">Nom:</Typography>
                        </Grid>
                        <Grid item xs={6}>
                            <Typography variant="body1">{userDetails.nom}</Typography>
                        </Grid>

                        <Grid item xs={6}>
                            <Typography variant="body1">Prénom:</Typography>
                        </Grid>
                        <Grid item xs={6}>
                            <Typography variant="body1">{userDetails.prenom}</Typography>
                        </Grid>

                        <Grid item xs={6}>
                            <Typography variant="body1">Email:</Typography>
                        </Grid>
                        <Grid item xs={6}>
                            <TextField
                                fullWidth
                                name="email"
                                value={formik.values.email}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                variant="outlined"
                                size="small"
                                error={formik.touched.email && Boolean(formik.errors.email)}
                                helperText={formik.touched.email && formik.errors.email}
                            />
                        </Grid>

                        <Grid item xs={6}>
                            <Typography variant="body1">Numéro de Téléphone:</Typography>
                        </Grid>
                        <Grid item xs={6}>
                            <TextField
                                fullWidth
                                name="numeroTelephone"
                                value={formik.values.numeroTelephone}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                variant="outlined"
                                size="small"
                                error={formik.touched.numeroTelephone && Boolean(formik.errors.numeroTelephone)}
                                helperText={formik.touched.numeroTelephone && formik.errors.numeroTelephone}
                            />
                        </Grid>

                        <Grid item xs={6}>
                            <Typography variant="body1">Nouveau mot de passe:</Typography>
                        </Grid>
                        <Grid item xs={6}>
                            <TextField
                                fullWidth
                                name="password"
                                type="password"
                                value={formik.values.password}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                variant="outlined"
                                size="small"
                                error={formik.touched.password && Boolean(formik.errors.password)}
                                helperText={formik.touched.password && formik.errors.password}
                            />
                        </Grid>

                        <Grid item xs={6}>
                            <Typography variant="body1">Confirmer le mot de passe:</Typography>
                        </Grid>
                        <Grid item xs={6}>
                            <TextField
                                fullWidth
                                name="passwordConfirm"
                                type="password"
                                value={formik.values.passwordConfirm}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                variant="outlined"
                                size="small"
                                error={formik.touched.passwordConfirm && Boolean(formik.errors.passwordConfirm)}
                                helperText={formik.touched.passwordConfirm && formik.errors.passwordConfirm}
                            />
                        </Grid>

                        <Grid item xs={6}>
                            <Typography variant="body1">Points de Fidélité:</Typography>
                        </Grid>
                        <Grid item xs={6}>
                            <Typography variant="body1">{userDetails.pointsFidelite}</Typography>
                        </Grid>

                        <Grid item xs={12}>
                            <Box sx={{ display: 'flex', justifyContent: 'center' }}>
                                <Button variant="contained" color="primary" type="submit">
                                    Save
                                </Button>
                            </Box>
                        </Grid>
                    </Grid>
                </Box>
            </Paper>
        </Container>
    );
}