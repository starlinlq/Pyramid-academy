import "./home.css";
import { useEffect, useState } from "react";
import client from "../api/client";

export default function Home() {
  const [companies, setCompanies] = useState([]);
  const [active, setActive] = useState(false);
  const [data, setData] = useState("");

  useEffect(() => {
    let companies = async () => {
      try {
        let companies = await client.company.getAll();
        setCompanies(companies.data);
      } catch (error) {
        console.log(error);
      }
    };

    companies();
  }, []);

  const handleForm = async (event) => {
    event.preventDefault();
    try {
      const request = await client.company.create(data);
      setCompanies([...companies, request.data]);
      setActive(false);
      setData("");
    } catch (error) {
      console.log(error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await client.company.deleteCompanyById(id);
      setCompanies(companies.filter((company) => company.id !== id));
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div
      className="container  d-flex justify-content-center align-items-center "
      style={{ height: "100vh" }}
    >
      <div className="companies">
        <h2 className="company_title">COMPANIES</h2>
        <ul style={{ padding: 0 }}>
          {companies.map((company, index) => (
            <li
              key={index}
              className="company border border-secondary rounded  pt-1 pb-1 mb-2 ps-2"
            >
              <div className="d-flex justify-content-between pe-2">
                <a className="link" href={`/company/${company.id}`}>
                  {company.name.toUpperCase()}
                </a>
                <button
                  onClick={() => handleDelete(company.id)}
                  type="button"
                  className="btn btn-danger"
                >
                  Delete
                </button>
              </div>
            </li>
          ))}
        </ul>

        {active && (
          <form className="form mt-2" onSubmit={handleForm}>
            <input
              type="text"
              className="form-control"
              id="inputEmail4"
              placeholder="Company name"
              value={data}
              onChange={(e) => setData(e.target.value)}
            />
          </form>
        )}
        <div className="d-flex mt-3">
          <button
            type="button"
            className="btn btn-dark  me-2"
            onClick={() => setActive(true)}
          >
            Add company
          </button>
          {active && (
            <button
              type="button"
              className="btn btn-danger"
              onClick={() => setActive(false)}
            >
              Cancel
            </button>
          )}
        </div>
      </div>
    </div>
  );
}
