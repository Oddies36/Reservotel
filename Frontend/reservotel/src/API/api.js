// src/api.js
import axios from 'axios';

export const apiClient = axios.create({
  baseURL: 'http://localhost:8080/',
  headers: {
    'Content-Type': 'application/json',
  },
});

export const login = async (username, password) => {
  try {
    const response = await apiClient.post('/login', null, {
      auth: {
        username,
        password
      }
    });
    return response;
  } catch (error) {
    throw error;
  }
};

export const submitFormData = async (formData) => {
  try {
    const loginResponse = await login('user', 'password');
    if (loginResponse.status === 200) {
      const response = await apiClient.post('/register', formData);
      return response;
    } else {
      throw new Error('Login failed');
    }
  } catch (error) {
    throw error;
  }
};