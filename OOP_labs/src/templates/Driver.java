package templates;

import factory.Annotation;

import java.util.ArrayList;

@Annotation(name = "Водитель")
public class Driver extends Employee {
    @Annotation(name = "Автомобиль")
    private ArrayList<Car> carsToDrive = new ArrayList<>();

    public Driver(){}

    public Driver(String name, int salary){
        super(name, salary);
    }

    public void setAllFields(String name, int salary, ArrayList<Car> cars){
        setName(name);
        setSalary(salary);
        setCarsToDrive(cars);
    }

    public void removeObject(CreatableObject objectToRemove) {
        if (carsToDrive.contains(objectToRemove)) {
            carsToDrive.remove(objectToRemove);
        }
    }

    public void setCarsToDrive(ArrayList carsToDrive) {
        this.carsToDrive = carsToDrive;
    }
}
