import React, { useState, useEffect } from 'react'
import {  useNavigate } from "react-router-dom"
import Header from "./Header"
function Register() {
    const navigate = useNavigate();
    useEffect(() => {
        if (localStorage.getItem('user-info')) {
            navigate('/add');
        }
    }, [])

    const [name, setName] = useState("");
    const [password, setPassword] = useState("");
    const [confirmpassword, setConfirmpassword] = useState("");
    const [email, setEmail] = useState("");
    const [SDT, setSDT] = useState("");
    const [Address, setAddress] = useState("");
    async function signUp() {
        let item = { name, password, email,SDT,Address };
        console.warn(item);
        if (password === confirmpassword) {
            let result = await fetch("http://quanlybanhangapi.herokuapp.com/api/register", {
                method: 'POST',
                body: JSON.stringify(item),
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                }
            })
            result = await result.json();
            // localStorage.setItem("user-info", JSON.stringify(result));
            // navigate('/productlist', { replace: true });
            if (JSON.stringify(result) !== JSON.stringify({ "Your password": "must contrain at least 5 characters!" })) {
                localStorage.setItem("user-info", JSON.stringify(result));
                navigate('/productlist', { replace: true });
            }
            else {
                alert("Vui lòng đặt mật khẩu trên 5 kí tự!")
            }
        }
        else {
            alert("Mật khẩu không trùng khớp!")
        }
    }
    return (
        <>
            <Header />
            <div className="col-sm-6 offset-sm-3">
                <h1>Multilevel Association</h1>
                <h3>Tạo tài khoản</h3>
                <input type="text" value={name} onChange={(e) => setName(e.target.value)} className="form-control" placeholder="Tên" />
                <br />
                <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} className="form-control" placeholder="Địa chỉ Email" />
                <br />
                <input type="text" value={SDT} onChange={(e) => setSDT(e.target.value)} className="form-control" placeholder="Số điện thoại" />
                <br />
                <input type="text" value={Address} onChange={(e) => setAddress(e.target.value)} className="form-control" placeholder="Địa chỉ" />
                <br />
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} className="form-control" placeholder="Mật khẩu" />
                <br />
                <input type="password" value={confirmpassword} onChange={(e) => setConfirmpassword(e.target.value)} className="form-control" placeholder="Nhập lại mật khẩu" />
                <br />
                <button onClick={signUp} className="btn btn-primary">Đăng ký</button>
            </div>
        </>
    )
}

export default Register