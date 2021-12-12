import Header from './Header'
import React, { useEffect, useState } from 'react'
// import { Table } from 'react-bootstrap'
// import { Link } from 'react-router-dom'
function Suadonhang() {
    const [data, setData] = useState([]);
    const [Address,setAddress] = useState([]);
    const idtest = JSON.parse(localStorage.getItem('donhang-info'));
    useEffect(() => {
        let donhang = JSON.parse(localStorage.getItem('donhang-info'));
        getData(donhang);
    }, [])
    async function editAddress(id){
        let item = { Address };
        let result = await fetch("https://quanlybanhangapi.herokuapp.com/api/v1/update/"+id,{
            method:"POST",
            body:JSON.stringify(item),
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            }
        });
    }
    async function getData(id) {
        let result = await fetch('https://quanlybanhangapi.herokuapp.com/api/v2/detail/'+id);
        result = await result.json();
        console.warn(result);
        setData(result);
    }
    return (
        <div>
            <Header />
            <div className="col-sm-3 offset-sm-4">
                <br />
                <h1>Sửa đơn hàng</h1>
                <img style={{ width: 200 }} src={data.ing_link} /> <br /><br />
                <p>Địa chỉ nhận hàng:</p>
                <input type='text'  onChange={(e) => setAddress(e.target.value)} className='form-control' defaultValue={data.Address} /><br /><br />
                <h5>{data.Accept?"Đã đặt hàng":"Chưa xác nhận đặt hàng"}</h5><br /><br />
                <button onClick={() => editAddress(idtest)} className='btn btn-primary'>Xác nhận</button>
            </div>
        </div>
    )
}
export default Suadonhang