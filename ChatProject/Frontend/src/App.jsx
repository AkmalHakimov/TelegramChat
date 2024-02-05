import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import { useState } from "react";
import { Link, Route,Routes } from "react-router-dom";
import Login from "./pages/Login/Login";
function App() {


  return (


    <div>
      <Link to={"/login"} className="btn btn-dark">Login</Link>
      <button className="btn btn-success">Home</button>
      <Routes>
        <Route element={<Login></Login>} path="/login"></Route>
      </Routes>
    </div>
  );
}

export default App;
