import './App.css';
import {BrowserRouter as Router, Navigate as Redirect, Route, Routes as Switch} from "react-router-dom";
import Login from "./Login";
import HomeClient from "./HomeClient";
import HomeAdmin from "./HomeAdmin";
import SignUp from "./SignUp";
import ChangePass from "./ChangePass";
import Cart from "./Cart";

function App() {
  const defaultRoute = window.location.pathname === "/" ? <Redirect to="/log-in"/> : undefined;

  return (
      <Router>
        <Switch>
          <Route exact path="/log-in" element={<Login/>}/>
          <Route exact path="/home/:email" element={<HomeClient/>}/>
          <Route exact path="/admin" element={<HomeAdmin/>}/>
          <Route exact path="/sign-up" element={<SignUp/>}/>
          <Route exact path="/changePassword" element={<ChangePass/>}/>
          <Route exact path="/cart/:email" element={<Cart/>}/>
        </Switch>
        {defaultRoute}
      </Router>
  );

}

export default App;
