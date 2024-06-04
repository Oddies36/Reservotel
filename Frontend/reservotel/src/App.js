import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import ForgotPassword from './Login/ForgotPassword';
import CreateNewUser from './Login/CreateNewUser';
import LandingPage from './Home/Home';
import Login from './Login/Login';
import HotelSearch from './HotelSearch/HotelSearch';
import HotelSelect from './HotelSelect/HotelSelect';


function App() {

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<Login/>} />
        <Route path="/mot-de-passe-oublie" element={<ForgotPassword/>} />
        <Route path="/register" element={<CreateNewUser/>} />
        <Route path="/home" element={<LandingPage/>} />
        <Route path="/hotels" element={<HotelSearch/>} />
        <Route path="/hotels/:pays/:ville/:idHotel" element={<HotelSelect/>} />
      </Routes>
    </Router>
  );
}

export default App;
