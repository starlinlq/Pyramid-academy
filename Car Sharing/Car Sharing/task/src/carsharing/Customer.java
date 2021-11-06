package carsharing;

public class Customer {
    private int id;
    private String name;
    private Integer rented_car_id;

    Customer(String name, int id, int rented_car_id){
        this.name = name;
        this.id = id;
        this.rented_car_id = rented_car_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRented_car_id(int rented_car_id) {
        this.rented_car_id = rented_car_id;
    }

    public int getRented_car_id() {
        return rented_car_id;
    }
}
