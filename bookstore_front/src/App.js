//import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Navigate as Redirect, Route, Routes as Switch} from "react-router-dom";
import Login from "./Login";
import HomeClient from "./HomeClient";
import SignUp from "./SignUp";
//import { BrowserRouter, Route, Routes } from 'react-router-dom'
//import { useEffect, useState } from 'react'


function App() {
  const defaultRoute = window.location.pathname === "/" ? <Redirect to="/log-in"/> : undefined;

  return (
      <Router>
        <Switch>
          <Route exact path="/log-in" element={<Login/>}/>
          <Route exact path="/home" element={<HomeClient/>}/>
          <Route exact path="/sign-up" element={<SignUp/>}/>
        </Switch>
        {defaultRoute}
      </Router>
  );

}

export default App;
