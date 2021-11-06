package carsharing;

import java.util.List;

public interface ICustomerCollection {
    public List<Customer> getAllCustomers();
    public Customer getSingleCustomer(int id);
    public void addCustomer(Customer customer);
}
