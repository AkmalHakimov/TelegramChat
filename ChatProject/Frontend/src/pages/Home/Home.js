import React from "react";
import ApiRequest from "../../tools/ApiRequest/ApiRequest";
import { useEffect, useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./Home.scss";
import Input from "../../tools/Input/Input";
import avatarImg from "../../asset/images/avatar.webp";
import avatarImg2 from "../../asset/images/avatar2.png";
import bg_image from "../../asset/images/bg_image.jpg";
import bg_image2 from "../../asset/images/bg_image2.jpg";
import bg_image3 from "../../asset/images/bg_image3.jpg";
import send_img from "../../asset/images/telegram-send-chat-media-message-icon.svg";
import SockJsClient from "react-stomp";

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
    getMessages(selectedUser.id)
  };
  return (
    <div className="big_box">
      <SockJsClient
        url="http://localhost:8080/ws"
        topics={["/topics/" + currentUserId]}
        onMessage={onMessage}
        onConnect={()=>console.log("ulandi")}
      />

      <div className="left_box">
        <div className="left_container">
          <div className="header">
            <div className="search_user">
              <Input placeholder="search"></Input>
            </div>
          </div>
          <div className="bottom">
            {users.map((item, index) => {
              return (
                <div
                  key={index}
                  onClick={() => selectUser(item)}
                  className={
                    "content" +
                    (selectedUser.id === item.id ? " bg-warning" : " ")
                  }
                >
                  <div className="avatar_img">
                    <img width={50} height={50} src={avatarImg} />
                  </div>
                  <div className="center_text">
                    <p>
                      <b>
                        {item.firstName} {item.lastName}
                      </b>
                    </p>
                    <p>{item.lastText}</p>
                  </div>
                  {item.count !== 0 ? (
                    <div className="right_text">
                      <div className="count">{item.count}</div>
                    </div>
                  ) : (
                    ""
                  )}
                </div>
              );
            })}
          </div>
        </div>
      </div>
      <div className="right_box">
        {selectedUser ? (
          <>
            <div className="top">
              <div className="left">
                <div className="avatar_img">
                  <img
                    width={50}
                    height={50}
                    style={{ borderRadius: 100 }}
                    src={avatarImg2}
                  />
                </div>
                <div>
                  <h4 style={{ color: "white" }}>
                    {selectedUser?.firstName} {selectedUser.lastName}
                  </h4>
                </div>
              </div>
            </div>
            <div className="bottom">
              {messages.map((item, index) => {
                return (
                  <div key={index}>
                    <div
                      style={
                        item.fromId === currentUserId
                          ? { marginLeft: 550, background: "#78E378" }
                          : { marginLeft: 10 }
                      }
                      className={"card w-50 my-2 p-2"}
                    >
                      <p>{item.text}</p>
                      <i style={{ fontSize: 15 }}>
                        {getExactTime(item.createdAt)}
                      </i>
                    </div>
                  </div>
                );
              })}
              <div className="post_msg">
                <input
                  value={sendMsgInp}
                  onChange={(e) => setSendMsgInp(e.target.value)}
                  className="post_inp"
                  placeholder="text..."
                  type="text"
                />
                <img
                  onClick={sendMessage}
                  style={{
                    position: "absolute",
                    right: 0,
                    top: 0,
                    cursor: "pointer",
                  }}
                  src={send_img}
                  width={50}
                  height={50}
                  alt=""
                />
              </div>
            </div>
          </>
        ) : (
          <div className="bg_image">
            <img className="image" src={bg_image2} />
          </div>
        )}
      </div>

      <ToastContainer></ToastContainer>
    </div>
  );
}
