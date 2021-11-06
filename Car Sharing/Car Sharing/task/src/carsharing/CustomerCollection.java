package carsharing;

import java.util.ArrayList;
import java.util.List;

public class CustomerCollection implements ICustomerCollection{
    private List<Customer> list;

    CustomerCollection(){
        this.list = new ArrayList<>();
    }

    @Override
    public void addCustomer(Customer customer){
        this.list.add(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return list;
    }

    @Override
    public Customer getSingleCustomer(int id) {
        return list.get(id);
    }
}
