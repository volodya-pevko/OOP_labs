package templates;

import factory.Annotation;

import java.util.ArrayList;

@Annotation(name = "Автопарк")
public class CarPark extends CreatableObject {
    @Annotation(name = "Автомобиль")
    private ArrayList<Car> cars = new ArrayList<>();
    @Annotation(name = "Сотрудник")
    private ArrayList<Employee> employees = new ArrayList<>();
    private int budget;

    public CarPark(){}

    public CarPark(String name, int budget){
        super(name);
        setBudget(budget);
        cars = new ArrayList<>();
        employees = new ArrayList<>();
    }

    public void setAllFields(String name, int budget, ArrayList<Car> cars, ArrayList<Employee> employees){
        setName(name);
        setBudget(budget);
        this.cars = cars;
        this.employees = employees;
    }

    public void removeObject(CreatableObject objectToRemove){
        if (cars.contains(objectToRemove)){
            cars.remove(objectToRemove);
        }
        if (employees.contains(objectToRemove)){
            employees.remove(objectToRemove);
        }
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public Car getCar(int arrIndex){
        return cars.get(arrIndex);
    }

    public Employee getEmployee(int arrIndex){
        return employees.get(arrIndex);
    }

    public int getBudget() {
        return budget;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(int arrIndex){
        cars.remove(arrIndex);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(int arrIndex){
        employees.remove(arrIndex);
    }

    public void setBudget(int budget){
        this.budget = budget;
    }
}
