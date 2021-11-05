package carsharing;

import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    private List<Company> companies;

    CompanyDaoImpl(){
        this.companies = new ArrayList<>();
    }

    public List<Company> getAllCompanies(){
        return companies;
    }
    public void addCompany(Company comp){
        this.companies.add(comp);
    }
    public void clearList(){
        this.companies.clear();
    }
}
