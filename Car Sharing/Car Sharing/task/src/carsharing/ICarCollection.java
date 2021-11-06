package carsharing;

import java.util.List;

public interface ICarCollection {
    public List<Car> getAllCars();
    public List<Car> filterCarById(int id);
    public void addCar(Car car);
    public Car getSingleCar(int id);
    public void clearCar();

    //testing
    public static String getSomething(){
        return "";
    }
    public default void sayHi(){
        System.out.println("hi");
    }
}
