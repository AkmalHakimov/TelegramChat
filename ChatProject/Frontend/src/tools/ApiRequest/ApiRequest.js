import axios from "axios"

const baseURL = "http://localhost:8080"

export default (url,method,data)=>{
    return axios({
        baseURL: baseURL,
        url,
        method,
        data,
        headers:{
            "key": localStorage.getItem("token")
        }
    })
}
