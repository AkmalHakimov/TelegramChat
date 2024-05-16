import {React,useState} from 'react'
import ApiRequest from '../../tools/ApiRequest/ApiRequest';
import {useNavigate} from "react-router-dom"
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import "../Login/login.css"
import {
  MDBBtn,
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBCard,
  MDBCardBody,
  MDBInput,
  MDBIcon,
  MDBCheckbox
}
from 'mdb-react-ui-kit';

export default function Login() {

  const [password,setPassword] = useState("");
  const [phone,setPhone] = useState("");

  const navigate = useNavigate();

    function LoginUser(){
        let obj = {
          phone:phone,
          password:password,
        }
        ApiRequest("/api/auth/login","POST",obj).then(res=>{
          localStorage.setItem("token",res.data)
            navigate("/home")
        }).catch(err=>{
          toast.error(err.response.data)
        })
    }
  return (
    <MDBContainer fluid>

      <MDBRow className='d-flex justify-content-center align-items-center h-100'>
        <MDBCol col='12'>

          <MDBCard className='bg-white my-5 mx-auto' style={{borderRadius: '1rem', maxWidth: '500px'}}>
            <MDBCardBody className='p-5 w-100 d-flex flex-column'>

              <h2 className="fw-bold mb-2 text-center">Sign in</h2>
              <p className="text-white-50 mb-3">Please enter your login and password!</p>

              <MDBInput value={phone} onChange={(e)=>setPhone(e.target.value)}  wrapperClass='mb-4 w-100' label='Email address' id='formControlLg' type='email' />
              <MDBInput value={password} onChange={(e)=>setPassword(e.target.value)} wrapperClass='mb-4 w-100' label='Password' id='formControlLg' type='password'/>

              <MDBCheckbox name='flexCheck' id='flexCheckDefault' className='mb-4' label='Remember password' />

              <MDBBtn onClick={()=>LoginUser()} size='lg'>
                Login
              </MDBBtn>


              <div style={{marginTop:"20px"}}>
                <p className="mb-0">Don't have an account? <a className="text-warning-50 fw-bold" style={{cursor:"pointer"}}>Sign Up</a></p>

              </div>

            </MDBCardBody>
          </MDBCard>

        </MDBCol>
      </MDBRow>

      <ToastContainer></ToastContainer>


    </MDBContainer>
  );
}
