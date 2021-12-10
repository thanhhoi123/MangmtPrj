import Header from './Header'
import React, { useEffect, useState } from 'react'
import { Table } from 'react-bootstrap'
// import { Link } from 'react-router-dom'
function ProductList() {
    const [data, setData] = useState([]);
    useEffect(() => {
        getData();
    }, [])
    async function deleteOperation(id) {
        // let result = await fetch("http://localhost:8000/api/delete/" + id, {
        //     method: 'DELETE'
        // })
        // result = await result.json();
        // console.warn('result', result);
        // getData();
    }
    async function getData() {
        let result = await fetch('https://quanlybanhangapi.herokuapp.com/api/v1/getSP');
        result = await result.json();
        setData(result);
    }
    return (
        <div>
            <Header />
            <div className="col-sm-8 offset-sm-2">
                <br />
                <h1>Dánh sách sản phẩm</h1>
                <Table striped bordered hover>
                    <thead className="thead-dark">
                    <tr>
                        <td>Id</td>
                        <td>Tên</td>
                        <td>Số lượng</td>
                        <td>Giá</td>
                        <td>Mô tả</td>
                        <td>Ảnh</td>
                        {/* <td>Ảnh</td> */}
                        {/* <td colSpan='2'>Operations</td> */}
                    </tr>
                    </thead>
                    {
                        data.map((item) =>
                            <tbody>
                            <tr>
                                <td>{item.id}</td>
                                <td>{item.name}</td>
                                <td>{item.amount}</td>
                                <td>{item.Price}</td>
                                <td>{item.description}</td>
                                <td><img style={{ width: 300 }} src={item.img_link} /></td>
                                <td>
                                    <span onClick={() => deleteOperation(item.id)} className='bought'>Mua</span>
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