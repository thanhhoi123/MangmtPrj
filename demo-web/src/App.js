// import logo from './logo.svg';
import './App.css';
// import { Button } from 'react-bootstrap';
// import Header from './Header'
import { BrowserRouter, Route, Routes} from 'react-router-dom'
import Login from './Login'
import Register from './Register'
// import AddProduct from './AddProduct'
// import UpdateProduct from './UpdateProduct'
import Protected from './Protected'
import ProductList from './ProductList'
import Dsachdonhang from './Dsachdonhang'
import Chitietdonhang from './Chitietdonhang'
import Suadonhang from './Suadonhang'
import Buyproduct from './Buyproduct'


// import SearchProduct from './SearchProduct'

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>

          {/* <Route path="/login">
            <Login />
          </Route> */}
          <Route path="/login" element={<Login/>} />

          <Route path="/register" element={<Register/>} />
          {/* <Route path="/register">
            <Register />
          </Route> */}


          {/* <Route path="/add">
            <Protected Cmp={AddProduct} />
          </Route>
          <Route path="/update/:id">
            <Protected Cmp={UpdateProduct} />
          </Route> */}
          {/* <Route path="/search">
            <Protected Cmp={SearchProduct} />
          </Route> */}

          <Route path="/productlist" element={<Protected Cmp={ProductList} />} />
          <Route path="/dsdonhang" element={<Protected Cmp={Dsachdonhang} />} />
          <Route path="/chitietdonhang" element={<Protected Cmp={Chitietdonhang} />} />
          <Route path="/suadonhang" element={<Protected Cmp={Suadonhang} />} />
          <Route path="/muasanpham" element={<Protected Cmp={Buyproduct} />} />

          
          {/* <Route path="/" element={<Protected Cmp={ProductList} />} /> */}
          <Route path="/" element={<Login/>} />

          {/* <Route path="/">
            <Protected Cmp={ProductList} />
          </Route> */}

        </Routes>
      </BrowserRouter>
    </div> 
  );
}
// function Profile() {
//   return (
//     <div>
//       <Outlet />
//     </div>
//   )
// }

export default App;
