package carsharing;

import java.util.List;

public interface CompanyDao {
    public List<Company> getAllCompanies();
    public void addCompany(Company comp);
    public void clearList();
}
