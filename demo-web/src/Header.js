import { Navbar, Nav, NavDropdown } from 'react-bootstrap'
import './App.css';
import { Link, useNavigate } from 'react-router-dom'
function Header() {
    const navigate = useNavigate();
    let user = JSON.parse(localStorage.getItem('user-info'));
    function logOut() {
        localStorage.clear();
        navigate('/login', {replace: true});
    }
    return (
        <div className='navbar_blog'>
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand href="#home" className='navbar_margin'>Multilevel Association</Navbar.Brand>
                <Nav className="me-auto">
                    {
                        localStorage.getItem("user-info") ?
                            <>
                                <Link to="/productlist" className="navbar_warapper" >List sản phẩm</Link>
                                <Link to="/dsdonhang" className="navbar_warapper" >List đơn hàng</Link>
                                {/* <Link to="/giohang" >Giỏ hàng</Link> */}

                                {/* <Link to="/add" >Add Products</Link>
                                <Link to="/update" >Update Products</Link>
                                <Link to="/search" >Search Products</Link> */}
                            </>
                            :
                            <>
                                <Link to="/login" className="navbar_warapper">Đăng nhập</Link>
                                <Link to="/register" >Đăng ký</Link>
                            </>
                    }
                </Nav>
                {localStorage.getItem("user-info") ?
                    <Nav className='navbar_margin'>

                        <NavDropdown title={user && user.name}>
                            {/* <NavDropdown.Item>Profile</NavDropdown.Item> */}
                            {/* <NavDropdown.Item>LogOut</NavDropdown.Item> */}
                            <NavDropdown.Item onClick={logOut}>Đăng xuất</NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                    : null}
            </Navbar>
        </div>
    )
}

export default Header