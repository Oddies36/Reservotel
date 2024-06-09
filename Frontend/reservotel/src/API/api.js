import axios from 'axios';

export const apiClient = axios.create({
  baseURL: 'http://localhost:8080/',
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true
});

let csrfToken = null;

export const fetchCsrfToken = async () => {
  try {
    const response = await apiClient.get('/csrf-token');
    csrfToken = response.data.token;
  } catch (error) {
    console.error('Error fetching CSRF token:', error);
  }
};

apiClient.interceptors.request.use((config) => {
  if (csrfToken) {
    config.headers['X-XSRF-TOKEN'] = csrfToken;
  }
  return config;
}, (error) => {
  return Promise.reject(error);
});

// export const login = async (username, password) => {
//   try {
//     await fetchCsrfToken();
//     const response = await apiClient.post('/login', null, {
//       auth: {
//         username,
//         password
//       }
//     });
//     return response;
//   } catch (error) {
//     throw error;
//   }
// };

// export const submitFormData = async (formData) => {
//   try {
//     const loginResponse = await login('user', 'password');
//     if (loginResponse.status === 200) {
//       const response = await apiClient.post('/register', formData);
//       return response;
//     } else {
//       throw new Error('Login failed');
//     }
//   } catch (error) {
//     throw error;
//   }
// };