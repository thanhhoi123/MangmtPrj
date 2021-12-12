import Header from './Header'
import React, { useEffect, useState } from 'react'
import { Table } from 'react-bootstrap'
// import { Link } from 'react-router-dom'
function Chitietdonhang() {
    const [data, setData] = useState([]);
    useEffect(() => {
        let donhang = JSON.parse(localStorage.getItem('donhang-info'));
        getData(donhang);
    }, []);
    async function getData(id) {
        let result = await fetch('https://quanlybanhangapi.herokuapp.com/api/v1/detail/' + id);
        result = await result.json();
        setData(result);
    }
    return (
        <div>
            <Header />
            <div className="col-sm-8 offset-sm-2">
                <br />
                <h1>Thông tin sản phẩm</h1>
                <Table striped bordered hover>
                    <thead className="thead-dark">
                        <tr>
                            <td>Ảnh</td>
                            <td>Tên sản phẩm</td>
                            <td>Số lượng</td>
                            <td>Đơn giá</td>
                            <td>Mô tả</td>
                        </tr>
                    </thead>
                    {
                        data.map((item) =>
                            <tbody>
                                <tr>
                                    <td><img style={{ width: 300 }} src={item.img_link} /></td>
                                    <td>{item.name}</td>
                                    <td>{item.amount}</td>
                                    <td>{item.Price}</td>
                                    <td>{item.description}</td>
                                </tr>
                            </tbody>
                        )
                    }
                </Table>
            </div>
        </div>
    )
}
export default Chitietdonhang