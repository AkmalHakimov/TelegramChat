import React from "react";
import ApiRequest from "../../tools/ApiRequest/ApiRequest";
import { useEffect,useState } from "react";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function Home() {

    const [users,setUsers] = useState([]);

    useEffect(()=>{
        getUsers();
    },[])

    const getUsers =()=>{
       ApiRequest("/api/user","get").then(res=>{
        setUsers(res.data)
       }).catch(err=>{
        console.log(err);
            toast.error(err.message)
       })
    }
  return (
    <div className="container my-5">
        <h1>Users</h1>
      <div className="card col-6">
        <ul style={{listStyle:'none'}} class="card-body p-4">
            {
                users.map((item,index)=>{
                    return <li style={{}} key={index}>
                        <p style={{fontSize:30}}>{item.firstName} {item.lastName} <span>{item.count}</span></p>
                    </li>
                })
            } 
        </ul>
      </div>
    <ToastContainer></ToastContainer>
    </div>  
  );
}
