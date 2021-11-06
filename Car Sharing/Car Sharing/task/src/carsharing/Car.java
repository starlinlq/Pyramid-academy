package carsharing;

public class Car {
    private String name;
    private int id;
    private int company_id;
    private boolean is_rented;

    Car(String name, int id, int company_id, boolean is_rented){
        this.name = name;
        this.id  = id;
        this.company_id = company_id;
        this.is_rented = is_rented;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public boolean is_rented() {
        return is_rented;
    }
}
