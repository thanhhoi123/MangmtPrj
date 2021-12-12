import Header from './Header'
import React, { useEffect, useState } from 'react'
import { Table } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom'
function Dsachdonhang() {
    const [data, setData] = useState([]);
    const navigate = useNavigate();
    useEffect(() => {
        let user = JSON.parse(localStorage.getItem('user-info'));
        getData(user.id);
    }, [])
    async function chitietdonhang(id) {
        localStorage.setItem("donhang-info", JSON.stringify(id));
        navigate('/chitietdonhang', { replace: true });
    }
    async function suadonhang(id) {
        localStorage.setItem("donhang-info", JSON.stringify(id));
        navigate('/suadonhang', { replace: true });
    }
    async function xoadonhang(id) {
        let result = await fetch("https://quanlybanhangapi.herokuapp.com/api/v1/delete/" + id)
        let user = JSON.parse(localStorage.getItem('user-info'));
        getData(user.id);
    }
    async function getData(id) {
        let result = await fetch('https://quanlybanhangapi.herokuapp.com/api/v1/getDH/'+id);
        result = await result.json();
        setData(result);
    }
    return (
        <div>
            <Header />
            <div className="col-sm-8 offset-sm-2">
                <br />
                <h1>Dánh sách đơn đặt hàng</h1>
                <Table striped bordered hover>
                    <thead className="thead-dark">
                    <tr>
                        <td>Mã đơn hàng</td>
                        <td>Địa chỉ nhận hàng</td>
                        <td>Tình trạng</td>
                    </tr>
                    </thead>
                    {
                        data.map((item) =>
                            <tbody>
                            <tr>
                                <td>{item.id}</td>
                                
                                <td>{item.Address}</td>
                                <td>{item.Accept? "Đã đặt hàng"  : "Chưa xác nhận"}</td>
                                <td>
                                    <span onClick={() => chitietdonhang(item.id)} className='chitiet'>Xem chi tiết</span>
                                </td>
                                <td>
                                    <span onClick={() => suadonhang(item.id)} className='chitiet'>Sửa đơn</span>
                                </td>
                                <td>
                                    <span onClick={() => xoadonhang(item.id)} className='chitiet'>Xóa</span>
                                </td>
                            </tr>
                            </tbody>
                        )
                    }
                </Table>
            </div>
        </div>
    )
}
export default Dsachdonhang