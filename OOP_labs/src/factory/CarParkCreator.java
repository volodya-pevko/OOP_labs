package factory;

import templates.Car;
import templates.CarPark;
import templates.CreatableObject;
import templates.Employee;

import java.util.ArrayList;
import java.util.HashMap;

@Annotation(name = "Автопарк")
public class CarParkCreator extends Creator {
    public CarPark createEmptyProduct(){
        return new CarPark();
    }

    public CarPark createProduct(ArrayList<String> fieldValues){
        return new CarPark(fieldValues.get(1),Integer.parseInt(fieldValues.get(0)));
    }

    public void editProduct(ArrayList<String> fieldValues, HashMap<String,ArrayList<CreatableObject>> composedArrayLists, CreatableObject creatableObject){
        CarPark myCarPark = (CarPark) creatableObject;
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<CreatableObject> carsToAdd = composedArrayLists.get("Автомобиль");
        ArrayList<CreatableObject> employeesToAdd = composedArrayLists.get("Сотрудник");
        for(CreatableObject creatableObject1: carsToAdd){
            cars.add((Car)creatableObject1);
        }
        for(CreatableObject creatableObject1: employeesToAdd){
            employees.add((Employee) creatableObject1);
        }
        myCarPark.setAllFields(fieldValues.get(1),Integer.parseInt(fieldValues.get(0)),cars,employees);
    }
}
