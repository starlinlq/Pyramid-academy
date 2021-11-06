package carsharing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarCollection implements ICarCollection{
    List<Car> list;

    CarCollection(){
        this.list = new ArrayList<>();
    }

    @Override
    public List<Car> getAllCars() {
        return this.list;
    }

    @Override
    public List<Car> filterCarById(int id){
        return this.list.stream().filter(car-> car.getCompany_id() == id).collect(Collectors.toList());
    }

    @Override
    public void addCar(Car car){
        this.list.add(car);
    }

    @Override
    public Car getSingleCar(int id){
        return null;
    }

    @Override
    public void clearCar(){
        this.list.clear();
    }
}
