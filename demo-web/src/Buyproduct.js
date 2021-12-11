import Header from './Header'
import React, { useEffect, useState } from 'react'
import { Table } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom'
function Buyproduct() {
    const [Data, setData] = useState([]);
    const [address,setAddress] = useState([]);
    const [SLSanPham,setAmount] = useState([]);
    const navigate = useNavigate();
    // const IDSanPham = [];
    // const SLSanPham = [];
    // const idtest = JSON.parse(localStorage.getItem('donhang-info'));
    useEffect(() => {
        let sanpham = JSON.parse(localStorage.getItem('idsanpham-info'));
        console.log(sanpham);
        getData(sanpham);
        console.log(Data);
    }, []);
    async function buyProduct(){
        const accept=1;
        let IDSanPham = JSON.parse(localStorage.getItem('idsanpham-info'));
        let user = JSON.parse(localStorage.getItem('user-info'));
        let item = {SLSanPham,address,accept,IDSanPham};
        let result = await fetch("https://quanlybanhangapi.herokuapp.com/api/v1/BuySP/"+user.id,{
            method:"POST",
            body:JSON.stringify(item),
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            }
        });
        navigate("/productlist",{replace:true});
    }
    // async function saveDH(){
    //     // const Accept=0;
        
    // }
    async function getData(id) {
        let result = await fetch('https://quanlybanhangapi.herokuapp.com/api/v1/SP/detail/' + id);
        result = await result.json();
        // console.warn(result);
        setData(result);
        // console.log(data);
    }
    return (
        <div>
            <Header />
            <div className="col-sm-6 offset-sm-3">
                <br />
                <h1>Đặt hàng</h1>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <td>Ảnh</td>
                            <td>Tên</td>
                            <td>Mô tả</td>
                            <td>Đơn giá</td>
                            <td>Số lượng</td>
                        </tr>
                    </thead>
                    {
                        Data.map((item) =>
                            <tbody>
                                <tr>
                                    <td><img style={{ width: 300 }} src={item.img_link} /></td>
                                    <td>{item.name}</td>
                                    <td>{item.description}</td>
                                    <td>{item.Price}</td>
                                    <td>
                                        <input type='text' onChange={(e) => setAmount(e.target.value)} className='form-control' />
                                    </td>
                                </tr>
                            </tbody>
                        )
                    }
                </Table>
                <br /><br />
                <p>Địa chỉ nhận hàng:</p>
                <input type='text' value={address} onChange={(e) => setAddress(e.target.value)} className='form-control' placeholder='Địa chỉ nhận hàng'/><br /><br />
                {/* <p>Số lượng:</p>
                <input type='text' value={amount} onChange={(e) => setAmount(e.target.value)} className='form-control' placeholder='Số lượng'/><br /><br /> */}
                <button onClick={() => buyProduct()} className='btn btn-primary' title='Mua ngay mặt hàng này'>Mua hàng</button>
                {/* <button onClick={() => saveDH()} className='btn btn-primary bt' title='Lưu lại để mua sau'>Lưu đơn hàng</button> */}
            </div>
        </div>
    )
}
export default Buyproduct