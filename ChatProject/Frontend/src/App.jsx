import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { useState } from "react";
import { Link, Route,Routes } from "react-router-dom";
import Login from "./pages/Login/Login";  
import Home from "./pages/Home/Home";
function App() {


  return (
    <div>
      <Routes>
        <Route element={<Login></Login>} path="/login"></Route>
        <Route element={<Home></Home>} path="/home"></Route>
      </Routes>
    </div>
  );
}

export default App;
