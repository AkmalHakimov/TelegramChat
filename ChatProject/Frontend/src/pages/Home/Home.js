import React from "react";
import ApiRequest from "../../tools/ApiRequest/ApiRequest";
import { useEffect, useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./Home.scss";
import avatarImg from "../../asset/images/avatar.webp";
import avatarImg2 from "../../asset/images/avatar2.png";
import bg_image from "../../asset/images/bg_image.jpg";
import bg_image2 from "../../asset/images/bg_image2.jpg";
import bg_image3 from "../../asset/images/bg_image3.jpg";
import send_img from "../../asset/images/telegram-send-chat-media-message-icon.svg";
import SockJsClient from "react-stomp";
import {
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBCard,
  MDBCardBody,
  MDBIcon,
  MDBBtn,
  MDBTypography,
  MDBTextArea,
  MDBCardHeader,
} from "mdb-react-ui-kit";

export default function Home() {
  const [users, setUsers] = useState([]);
  const [messages, setMessages] = useState([]);
  const [currentUserId, setCurrentUserId] = useState("");
  const [selectedUser, setSelectedUser] = useState("");
  const [sendMsgInp, setSendMsgInp] = useState("");

  useEffect(() => {
    getUsers();
    let x = localStorage.getItem("token");
    let y = localStorage.getItem("user");
    if (x !== null) {
      setCurrentUserId(x);
    }
    if (y !== null) {
      setSelectedUser(JSON.parse(y));
      getMessages(JSON.parse(y).id);
    }
  }, []);

  function getMessages(id) {
    ApiRequest("/api/message/" + id, "get")
      .then((res) => {
        setMessages(res.data);
      })
      .catch((err) => {
        toast.error(err.response);
      });
  }

  const getUsers = () => {
    ApiRequest("/api/user", "get")
      .then((res) => {
        setUsers(res.data);
      })
      .catch((err) => {
        console.log(err);
        toast.error(err.message);
      });
  };

  function selectUser(user) {
    setSelectedUser(user);
    localStorage.setItem("user", JSON.stringify(user));
    getMessages(user.id);
    getUsers();
  }

  function getExactTime(item) {
    let date = item?.substring(0, 10);
    let time = item?.substring(11, 19);
    return date + " " + time;
  }

  function sendMessage() {
    ApiRequest("/api/message", "post", {
      toId: selectedUser.id,
      text: sendMsgInp,
    })
      .then((res) => {
        getMessages(selectedUser.id);
        getUsers();
        setSendMsgInp("");
      })
      .catch((err) => {
        toast.error(err.response.data);
      });
  }

  const onMessage = (msg) => {
    getUsers()
    console.log("salom");
    getMessages(selectedUser.id)
  };
  return (
    <>
     <SockJsClient
        url="http://localhost:8080/ws"
        topics={["/topics/" + currentUserId]}
        onMessage={onMessage}
        onConnect={()=>console.log("ulandi")}
      />

<MDBContainer fluid className="py-5" style={{ backgroundColor: "#eee" }}>
     
     <MDBRow>
       <MDBCol md="6" lg="5" xl="4" className="mb-4 mb-md-0">
         <h5 className="font-weight-bold mb-3 text-center text-lg-start">
           Member
         </h5>

         <MDBCard>
           <MDBCardBody>
             <MDBTypography listUnStyled className="mb-0">
               {
                 users.map((item,index)=>{
                   return(
                     <li
                 className="p-2 border-bottom"
                 // style={{ backgroundColor: "#eee" }}
                 key={index}
                 onClick={() => selectUser(item)}
               >
                 <a href="#!" className="d-flex justify-content-between">
                   <div className="d-flex flex-row">
                     <img
                       src={avatarImg}
                       alt="avatar"
                       className="rounded-circle d-flex align-self-center me-3 shadow-1-strong"
                       width="60"
                     />
                     <div className="pt-1">
                       <p className="fw-bold mb-0">{item.firstName} {item.lastName}</p>
                       <p className="small text-muted">
                       {item.lastText}
                       </p>
                     </div>
                   </div>
                   <div className="pt-1">
                     {/* <p className="small text-muted mb-1">Just now</p> */}
                     {item.count !== 0 ? (
                  <span className="badge bg-danger float-end">{item.count}</span>
                 ) : (
                   ""  
                 )}
                     
                   </div>
                 </a>
               </li>
                   )
                 })
               }
               
               <li className="p-2 border-bottom">
                 <a href="#!" className="d-flex justify-content-between">
                   <div className="d-flex flex-row">
                     <img
                       src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/avatar-4.webp"
                       alt="avatar"
                       className="rounded-circle d-flex align-self-center me-3 shadow-1-strong"
                       width="60"
                     />
                     <div className="pt-1">
                       <p className="fw-bold mb-0">Kate Moss</p>
                       <p className="small text-muted">
                         Lorem ipsum dolor sit.
                       </p>
                     </div>
                   </div>
                   <div className="pt-1">
                     <p className="small text-muted mb-1">Yesterday</p>
                   </div>
                 </a>
               </li>
               <li className="p-2 border-bottom">
                 <a href="#!" className="d-flex justify-content-between">
                   <div className="d-flex flex-row">
                     <img
                       src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/avatar-5.webp"
                       alt="avatar"
                       className="rounded-circle d-flex align-self-center me-3 shadow-1-strong"
                       width="60"
                     />
                     <div className="pt-1">
                       <p className="fw-bold mb-0">Lara Croft</p>
                       <p className="small text-muted">
                         Lorem ipsum dolor sit.
                       </p>
                     </div>
                   </div>
                   <div className="pt-1">
                     <p className="small text-muted mb-1">Yesterday</p>
                   </div>
                 </a>
               </li>
               <li className="p-2">
                 <a href="#!" className="d-flex justify-content-between">
                   <div className="d-flex flex-row">
                     <img
                       src={avatarImg2}
                       alt="avatar"
                       className="rounded-circle d-flex align-self-center me-3 shadow-1-strong"
                       width="60"
                     />
                     <div className="pt-1">
                       <p className="fw-bold mb-0">Brad Pitt</p>
                       <p className="small text-muted">
                         Lorem ipsum dolor sit.
                       </p>
                     </div>
                   </div>
                   <div className="pt-1">
                     <p className="small text-muted mb-1">5 mins ago</p>
                     <span className="text-muted float-end">
                       <MDBIcon fas icon="check" />
                     </span>
                   </div>
                 </a>
               </li>
             </MDBTypography>
           </MDBCardBody>
         </MDBCard>
       </MDBCol>

       <MDBCol  md="6" lg="7" xl="8">
         <MDBTypography style={{height:"91vh",overflowX:"auto"}} listUnStyled>
           {
             messages.map((item,index)=>{
               return (
               <li key={index} className="d-flex justify-content-between mb-4">
                 {/* {
                   item.fromId.id === currentUserId?
                   <img
                   src={avatarImg}
                   alt="avatar"
                   className="rounded-circle d-flex align-self-start me-3 shadow-1-strong"
                   width="60"
                 />
                 :""
                 } */}
               <MDBCard style={
                       item.fromId.id === currentUserId
                         ? { marginLeft: 450, background: "#78E378",width:"800px" }
                         : { marginLeft: 10,width:"500px" }
                     }>
                 <MDBCardHeader className="d-flex justify-content-between p-3">
                   <p className="fw-bold mb-0">{item.fromId?.firstName} {item.fromId.lastName}</p>
                   <p className="text-muted small mb-0">
                     <i><MDBIcon far icon="clock" />{getExactTime(item.createdAt)}</i>
                   </p>
                 </MDBCardHeader>
                 <MDBCardBody>
                   <p className="mb-0">
                     {item.text}
                   </p>
                 </MDBCardBody>
               </MDBCard>
               {/* {
                   item.fromId.id === currentUserId?
                   <img
                   src={avatarImg2}
                   alt="avatar"
                   className="rounded-circle d-flex align-self-start me-3 shadow-1-strong"
                   width="60"
                 />
                 :""
                 } */}
             </li>
               )
             })
           }
           
           <li className="bg-white mb-3">
             <MDBTextArea value={sendMsgInp} onChange={(e)=>setSendMsgInp(e.target.value)} label="Message" id="textAreaExample" rows={4} />
           </li>
           <MDBBtn onClick={sendMessage} color="info" rounded className="float-end">
             Send
           </MDBBtn>
         </MDBTypography>
       </MDBCol>
     </MDBRow>
     <ToastContainer></ToastContainer>
   </MDBContainer>
    </>
    
  );
}
