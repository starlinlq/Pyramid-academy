import axios from "axios";

const URL = "http://localhost:8889/api/v1/";
const requests = {
  get: (url) => axios.get(url),
  post: (url, data) => axios.post(url, data),
  delete: (url) => axios.delete(url),
};

const company = {
  getAll: () => requests.get(URL + "company"),
  create: (data) => requests.post(URL + "company", { name: data }),
  getById: (id) => requests.get(URL + `company/${id}`),
  createEmployee: (employee) => requests.post(URL + "employee", employee),
  deleteCompanyById: (id) => requests.delete(URL + `company/${id}`),
};

const client = { company };

export default client;
