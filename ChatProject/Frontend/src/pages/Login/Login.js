import React from 'react'
import ApiRequest from '../../tools/ApiRequest/ApiRequest';
import {useNavigate} from "react-router-dom"
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function Login() {

  const navigate = useNavigate();

    function handleSubmit(e){
        e.preventDefault();
        let obj = {
          phone: e.target[0].value,
          password: e.target[1].value,
        }
        ApiRequest("/api/auth/login","POST",obj).then(res=>{
          localStorage.setItem("token",res.data)
            navigate("/home")
        }).catch(err=>{
          toast.error(err.response.data)
        })
    }
  return (
    <div>
        <h1 className='my-4'>Login</h1>

        <form style={{width:400}} onSubmit={handleSubmit}>
            <input className='form-control my-2' type="text" placeholder='phone' />
            <input className='form-control my-2' type="text" placeholder='password' />
            <button className='btn btn-primary'>Save</button>
        </form>
    <ToastContainer></ToastContainer>
    </div>
  )
}
