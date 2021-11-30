import { useParams } from "react-router";
import { useEffect, useState } from "react";
import client from "../api/client";
import format from "date-format";

function Company() {
  const { companyId } = useParams();
  const [company, setCompany] = useState({ name: "", list: [] });
  const [active, setActive] = useState(false);
  const [data, setData] = useState({ name: "", age: "" });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const getById = async (id) => {
      try {
        const company = await client.company.getById(id);
        setCompany(company.data);
        setLoading(false);
      } catch (error) {
        console.log(error);
        setLoading(false);
      }
    };
    getById(companyId);
  }, [companyId]);

  const handleForm = async (event) => {
    event.preventDefault();

    if (data.name.length > 3 && data.age > 18) {
      try {
        const employee = await client.company.createEmployee({
          employee: data,
          companyName: company.name,
        });

        setCompany({ ...company, list: [...company.list, employee.data] });
        setData({ name: "", age: "" });
        setActive(false);
      } catch (error) {
        console.log(error);
      }
    }
  };

  if (loading) {
    return (
      <div
        className="container  d-flex justify-content-center align-items-center "
        style={{ height: "100vh" }}
      >
        <div className="companies d-flex justify-content-center">
          <div className="spinner-border" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      </div>
    );
  }
  return (
    <div
      className="container  d-flex justify-content-center align-items-center "
      style={{ height: "100vh" }}
    >
      <div className="companies">
        <h1 className="company_title">{company.name.toUpperCase()}</h1>
        <table className="table table-dark">
          <thead>
            <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Age</th>
              <th>Hired date</th>
            </tr>
          </thead>
          <tbody>
            {company.list.map(({ age, dateHired, id, name }, index) => (
              <tr key={id}>
                <th>{id}</th>
                <td>{name}</td>
                <td>{age}</td>
                <td>{new Date(dateHired).toDateString()}</td>
              </tr>
            ))}
          </tbody>
        </table>
        {active && (
          <>
            <form onSubmit={handleForm}>
              <input
                type="text"
                className="form-control"
                id="inputEmail4"
                placeholder="Name"
                value={data.name}
                onChange={(e) => setData({ ...data, name: e.target.value })}
              />
              <input
                type="text"
                className="form-control mt-2 mb-2"
                id="inputEmail4"
                placeholder="Age"
                value={data.age}
                onChange={(e) => setData({ ...data, age: e.target.value })}
              />
              <button type="submit" className="btn btn-primary me-2">
                submit
              </button>
              <button
                type="button"
                onClick={() => setActive(false)}
                className="btn btn-secondary"
              >
                cancel
              </button>
            </form>
          </>
        )}
        {!active && (
          <button
            onClick={() => setActive(true)}
            type="button"
            className="btn btn-secondary"
          >
            Add employee
          </button>
        )}
      </div>
    </div>
  );
}

export default Company;
