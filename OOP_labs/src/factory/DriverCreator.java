package factory;

import templates.Car;
import templates.CreatableObject;
import templates.Driver;

import java.util.ArrayList;
import java.util.HashMap;

@Annotation(name = "Водитель")
public class DriverCreator extends Creator {
    public Driver createEmptyProduct(){
        return new Driver();
    }

    public Driver createProduct(ArrayList<String> fieldValues){
        return new Driver(fieldValues.get(1),Integer.parseInt(fieldValues.get(0)));
    }

    public void editProduct(ArrayList<String> fieldValues, HashMap<String,ArrayList<CreatableObject>> composedArrayLists, CreatableObject creatableObject){
        Driver myDriver = (Driver) creatableObject;
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<CreatableObject> carsToAdd = composedArrayLists.get("Автомобиль");
        for(CreatableObject creatableObject1: carsToAdd){
            cars.add((Car)creatableObject1);
        }
        myDriver.setAllFields(fieldValues.get(1),Integer.parseInt(fieldValues.get(0)),cars);
    }
}
