import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import ForgotPassword from './Login/ForgotPassword';
import CreateNewUser from './Login/CreateNewUser';
import LandingPage from './Home/Home';
import Login from './Login/Login';
import HotelSearch from './HotelSearch/HotelSearch';
import HotelSelect from './HotelSelect/HotelSelect';
import Profile from './ProfilePage/Profile';
import ProtectedRoute from './Components/ProtectedRoute';
import Reservations from './ProfilePage/Reservations';
import Informations from './ProfilePage/Informations';
import ProfileContent from './ProfilePage/ProfileContent';
import Contact from './Contact/Contact';
import ContactContent from './Contact/ContactContent';


function App() {

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/auth/login" />} />
        <Route path="/auth/login" element={<Login/>} />
        <Route path="/mot-de-passe-oublie" element={<ForgotPassword/>} />
        <Route path="/register" element={<CreateNewUser/>} />
        <Route path="/home" element={<ProtectedRoute> <LandingPage/> </ProtectedRoute>} />
        <Route path="/hotels" element={<ProtectedRoute> <HotelSearch/> </ProtectedRoute>} />
        <Route path="/hotels/:pays/:ville/:idHotel" element={<ProtectedRoute> <HotelSelect/> </ProtectedRoute>} />
        <Route path="/profile" element={<ProtectedRoute> <Profile/> </ProtectedRoute>}>
          <Route path="reservations" element={<Reservations />} />
          <Route path="informations" element={<Informations />} />
          <Route index element={<ProfileContent />} />
        </Route>
        <Route path="/contact" element={<ProtectedRoute> <Contact/> </ProtectedRoute>}>
          <Route index element={<ContactContent />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
