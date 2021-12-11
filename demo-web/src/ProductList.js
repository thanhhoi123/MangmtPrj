import Header from './Header'
import React, { useEffect, useState } from 'react'
import { Table } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom'
function ProductList() {
    const [data, setData] = useState([]);
    const navigate = useNavigate();
    useEffect(() => {
        getData();
    }, [])
    async function buyProduct(id) {
        // let result = await fetch("http://localhost:8000/api/delete/" + id, {
        //     method: 'DELETE'
        // })
        // result = await result.json();
        // console.warn('result', result);
        // getData();
        localStorage.setItem("idsanpham-info", JSON.stringify(id));        
        navigate("/muasanpham",{replace: true});
    }
    async function getData() {
        let result = await fetch('https://quanlybanhangapi.herokuapp.com/api/v1/getSP');
        result = await result.json();
        setData(result);
    }
    return (
        <div>
            <Header />
            <div className="col-sm-6 offset-sm-3">
                <br />
                <h1>Dánh sách sản phẩm</h1>
                <Table striped bordered hover>
                    <thead className="thead-dark">
                    <tr>
                        <td>Ảnh</td>
                        <td>Tên</td>
                        <td>Số lượng</td>
                        <td>Đơn giá</td>
                        {/* <td>Mô tả</td> */}
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
                                {/* <td>{item.description}</td> */}
                                <td>
                                    <span onClick={() => buyProduct(item.id)} className='bought'>Mua</span>
                                </td>
                                {/* <td>
                                    <Link to={'update/' + item.id}>
                                        <span className='update'>Update</span>
                                    </Link>
                                </td> */}
                            </tr>
                            </tbody>
                        )
                    }
                </Table>
            </div>
        </div>
    )
}
export default ProductList