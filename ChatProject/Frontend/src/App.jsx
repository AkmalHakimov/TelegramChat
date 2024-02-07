import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { useState } from "react";
import { Link, Route,Routes } from "react-router-dom";
import Login from "./pages/Login/Login";  
import Home from "./pages/Home/Home";
function App() {


  return (
    <div>
      <Link to={"/login"} className="btn btn-dark m-3">Login</Link>
      <Link to={"/home"} className="btn btn-success">Home</Link>
      <Routes>
        <Route element={<Login></Login>} path="/login"></Route>
        <Route element={<Home></Home>} path="/home"></Route>
      </Routes>
    </div>
  );
}

export default App;
