package templates;

import factory.Annotation;

import java.util.ArrayList;

@Annotation(name = "Механик")
public class Mechanic extends Employee {
    @Annotation(name = "Автомобиль")
    private ArrayList<Car> carsToRepair = new ArrayList<>();

    public Mechanic(){}

    public Mechanic(String name, int salary){
        super(name, salary);
    }

    public void setAllFields(String name, int salary, ArrayList<Car> cars){
        setName(name);
        setSalary(salary);
        setCarsToRepair(cars);
    }

    public void removeObject(CreatableObject objectToRemove) {
        if (carsToRepair.contains(objectToRemove)) {
            carsToRepair.remove(objectToRemove);
        }
    }

    public void setCarsToRepair(ArrayList<Car> carsToRepair) {
        this.carsToRepair = carsToRepair;
    }
}
