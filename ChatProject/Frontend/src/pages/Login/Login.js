import React from 'react'

export default function Login() {

    function handleSubmit(e){
        e.preventDefault();
        console.log("salom");

    }
  return (
    <div>
        <h1>Login</h1>

        <form onSubmit={handleSubmit}>
            <input className='form_control' type="text" placeholder='phone my-2' />
            <input className='form_control' type="text" placeholder='password my-2' />
            <button className='btn btn-primary'>Save</button>
        </form>
    </div>
  )
}
