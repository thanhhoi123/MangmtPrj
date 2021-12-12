import React, { useState, useEffect } from 'react'
import { useNavigate  } from "react-router-dom"
import Header from "./Header"
function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    // const history = useHistory();
    const navigate = useNavigate();
    useEffect(() => {
        if (localStorage.getItem("user-info")) {
            // history.push("/add");
            navigate('/productlist', {replace: true});
        }
    }, [])
    async function login() {
        let item = { email, password }

        // let result = await fetch("http://localhost:8000/api/login", {
        let result = await fetch("https://quanlybanhangapi.herokuapp.com/api/login", {
            method: 'POST',
            body: JSON.stringify(item),
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            }
        })
        result = await result.json()
        if (JSON.stringify(result) !== JSON.stringify({ "error":"Wrong password or email address" })) {
            localStorage.setItem("user-info", JSON.stringify(result));
            navigate('/productlist', {replace: true});
        }
        else {
            alert("Lỗi : Email hoặc mật khẩu không chính xác!")
        }
    }
    return (
        <>
            <Header />
                <br />
            <h1 className="">Multilevel Association</h1>
                <br />
            <div className='col-sm-4 offset-sm-4'>
                <input type='text' value={email} onChange={(e) => setEmail(e.target.value)} placeholder='Địa chỉ Email' className='form-control' />
                <br />
                <input type='password' value={password} onChange={(e) => setPassword(e.target.value)} placeholder='Mật khẩu' className='form-control' />
                <br />
                <button onClick={login} className='btn btn-primary'>Đăng nhập</button>
            </div>
        </>
    )
}

export default Login