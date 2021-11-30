import "./App.css";
import Home from "./components/Home";
import Layout from "./layout/Layout";
import { Routes, Route, Link } from "react-router-dom";
import Company from "./components/company";

function App() {
  return (
    <Layout>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/company/:companyId" element={<Company />} />
      </Routes>
    </Layout>
  );
}

export default App;
