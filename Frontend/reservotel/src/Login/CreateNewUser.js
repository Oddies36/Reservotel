import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { apiClient } from '../API/api';
import LoginLayout from './LoginLayout';
import { Link as DomLink } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import * as Yup from 'yup';
import { useFormik } from 'formik';
import { fetchCsrfToken } from '../API/api';

const validationSchema = Yup.object({
    nom: Yup.string().required('Le nom est requis'),
    prenom: Yup.string().required('Le prénom est requis'),
    dateNaissance: Yup.date().required('La date de naissance est requise'),
    numeroTelephone: Yup.string().required('Le numéro de téléphone est requis'),
    email: Yup.string().email('Adresse mail invalide')
        .matches(/^[^\s@]+@[^\s@]+\.[^\s@]+$/, 'Adresse mail invalide')
        .required('L\'adresse mail est requise'),
    password: Yup.string()
        .min(8, 'Le mot de passe doit contenir au moins 8 caractères')
        .matches(/[A-Z]/, 'Le mot de passe doit contenir au moins une majuscule')
        .matches(/[a-z]/, 'Le mot de passe doit contenir au moins une minuscule')
        .matches(/[0-9]/, 'Le mot de passe doit contenir au moins un chiffre')
        .matches(/[!@#+$%^&*]/, 'Le mot de passe doit contenir au moins un caractère spécial')
        .required('Le mot de passe est requis'),
    passwordRepeat: Yup.string().oneOf([Yup.ref('password'), null], 'Les mots de passe ne correspondent pas').required('La confirmation du mot de passe est requise'),
    rue: Yup.string().required('La rue est requise'),
    numero: Yup.string().required('Le numéro est requis'),
    codePostal: Yup.string().required('Le code postal est requis'),
    ville: Yup.string().required('La ville est requise'),
    pays: Yup.string().required('Le pays est requis')
});

export default function CreateNewUser() {
    const navigate = useNavigate();


    const formik = useFormik({
        initialValues: {
            nom: '',
            prenom: '',
            dateNaissance: '',
            numeroTelephone: '',
            email: '',
            password: '',
            passwordRepeat: '',
            rue: '',
            numero: '',
            boite: '',
            codePostal: '',
            ville: '',
            pays: ''
        },


        validationSchema: validationSchema,
        onSubmit: async (values) => {
            const Adresse = {
                rue: values.rue,
                numero: values.numero,
                boite: values.boite,
                codePostal: values.codePostal,
                ville: values.ville,
                pays: values.pays
            };
            const Client = {
                nom: values.nom,
                prenom: values.prenom,
                dateNaissance: values.dateNaissance,
                numeroTelephone: values.numeroTelephone,
                email: values.email,
                password: values.password
            };
            const addrClientDTO = {
                client: Client,
                adresse: Adresse
            };

            try {
                await fetchCsrfToken();
                const response = await apiClient.post('/register', addrClientDTO);

                if (response.data === 'ok') {
                    Swal.fire({
                        title: 'Compte créé avec succès',
                        text: 'Vous allez être redirigé vers la page de connexion',
                        icon: 'success',
                        confirmButtonText: 'OK'
                    }).then(() => {
                        navigate('/login');
                    });
                }
                else if (response.data === 'nok') {
                    console.error('Création de compte échouée');
                }
                else {
                    console.error('Unknown response status');
                    console.error(response.data)
                }
            } catch (error) {
                console.error(error);
            }
        },
    });

    return (
        <LoginLayout title="Création de compte">
            <Box component="form" noValidate onSubmit={formik.handleSubmit} sx={{ mt: 1, width: '100%' }}>

                <Grid container spacing={2}>
                    <Grid item xs={6}>
                        <Grid container direction="column">
                            <Grid item>
                                <TextField
                                    margin="dense"
                                    required
                                    fullWidth
                                    id="nom"
                                    label="Nom"
                                    name="nom"
                                    autoComplete="family-name"
                                    type="text"
                                    size='small'
                                    value={formik.values.nom}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    error={formik.touched.nom && Boolean(formik.errors.nom)}
                                    helperText={formik.touched.nom && formik.errors.nom}
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    margin="dense"
                                    required
                                    fullWidth
                                    name="prenom"
                                    label="Prénom"
                                    id="prenom"
                                    autoComplete="given-name"
                                    type="text"
                                    size='small'
                                    value={formik.values.prenom}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    error={formik.touched.prenom && Boolean(formik.errors.prenom)}
                                    helperText={formik.touched.prenom && formik.errors.prenom}
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    margin="dense"
                                    required
                                    fullWidth
                                    name="email"
                                    label="Adresse mail"
                                    id="email"
                                    autoComplete="email"
                                    type="text"
                                    size='small'
                                    value={formik.values.email}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    error={formik.touched.email && Boolean(formik.errors.email)}
                                    helperText={formik.touched.email && formik.errors.email}
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    margin="dense"
                                    required
                                    fullWidth
                                    name="dateNaissance"
                                    label="Date de naissance"
                                    id="dateNaissance"
                                    type="date"
                                    size='small'
                                    InputLabelProps={{
                                        shrink: true,
                                    }}
                                    value={formik.values.dateNaissance}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    error={formik.touched.dateNaissance && Boolean(formik.errors.dateNaissance)}
                                    helperText={formik.touched.dateNaissance && formik.errors.dateNaissance}
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    margin="dense"
                                    required
                                    fullWidth
                                    name="numeroTelephone"
                                    label="Numéro de téléphone"
                                    id="numTelephone"
                                    autoComplete="tel"
                                    type="text"
                                    size='small'
                                    value={formik.values.numeroTelephone}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    error={formik.touched.numeroTelephone && Boolean(formik.errors.numeroTelephone)}
                                    helperText={formik.touched.numeroTelephone && formik.errors.numeroTelephone}
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    margin="dense"
                                    required
                                    fullWidth
                                    name="password"
                                    label="Mot de passe"
                                    id="password"
                                    type="password"
                                    size='small'
                                    value={formik.values.password}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    error={formik.touched.password && Boolean(formik.errors.password)}
                                    helperText={formik.touched.password && formik.errors.password}
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    margin="dense"
                                    required
                                    fullWidth
                                    name="passwordRepeat"
                                    label="Confirmer le mot de passe"
                                    id="passwordRepeat"
                                    type="password"
                                    size='small'
                                    value={formik.values.passwordRepeat}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    error={formik.touched.passwordRepeat && Boolean(formik.errors.passwordRepeat)}
                                    helperText={formik.touched.passwordRepeat && formik.errors.passwordRepeat}
                                />
                            </Grid>
                        </Grid>
                    </Grid>

                    <Grid item xs={6}>
                        <Grid container direction="column">
                            <Grid item>
                                <TextField
                                    margin="dense"
                                    required
                                    fullWidth
                                    id="rue"
                                    label="Rue"
                                    name="rue"
                                    autoComplete="address-line1"
                                    type="text"
                                    size='small'
                                    value={formik.values.rue}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    error={formik.touched.rue && Boolean(formik.errors.rue)}
                                    helperText={formik.touched.rue && formik.errors.rue}
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    margin="dense"
                                    required
                                    fullWidth
                                    name="numero"
                                    label="Numéro"
                                    id="numero"
                                    type="text"
                                    size='small'
                                    value={formik.values.numero}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    error={formik.touched.numero && Boolean(formik.errors.numero)}
                                    helperText={formik.touched.numero && formik.errors.numero}
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    margin="dense"
                                    fullWidth
                                    name="boite"
                                    label="Boite"
                                    id="boite"
                                    type="text"
                                    size='small'
                                    value={formik.values.boite}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    error={formik.touched.boite && Boolean(formik.errors.boite)}
                                    helperText={formik.touched.boite && formik.errors.boite}
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    margin="dense"
                                    required
                                    fullWidth
                                    name="codePostal"
                                    label="Code postal"
                                    id="codePostal"
                                    type="text"
                                    size='small'
                                    value={formik.values.codePostal}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    error={formik.touched.codePostal && Boolean(formik.errors.codePostal)}
                                    helperText={formik.touched.codePostal && formik.errors.codePostal}
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    margin="dense"
                                    required
                                    fullWidth
                                    name="ville"
                                    label="Ville"
                                    id="ville"
                                    type="text"
                                    size='small'
                                    value={formik.values.ville}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    error={formik.touched.ville && Boolean(formik.errors.ville)}
                                    helperText={formik.touched.ville && formik.errors.ville}
                                />
                            </Grid>
                            <Grid item>
                                <TextField
                                    margin="dense"
                                    required
                                    fullWidth
                                    name="pays"
                                    label="Pays"
                                    id="pays"
                                    type="text"
                                    size='small'
                                    value={formik.values.pays}
                                    onChange={formik.handleChange}
                                    onBlur={formik.handleBlur}
                                    error={formik.touched.pays && Boolean(formik.errors.pays)}
                                    helperText={formik.touched.pays && formik.errors.pays}
                                />
                            </Grid>
                        </Grid>
                    </Grid>
                </Grid>

                <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    sx={{ mt: 3, mb: 2 }}
                >
                    Créer mon compte
                </Button>
                <Grid container>
                    <Grid item xs>
                        <DomLink to="/auth/login">
                            Retour à la connexion
                        </DomLink>
                    </Grid>
                </Grid>
            </Box>
        </LoginLayout>
    );
}