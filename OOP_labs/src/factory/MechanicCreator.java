package factory;

import templates.Car;
import templates.CreatableObject;
import templates.Mechanic;

import java.util.ArrayList;
import java.util.HashMap;

@Annotation(name = "Механик")
public class MechanicCreator extends Creator {
    public Mechanic createEmptyProduct(){
        return new Mechanic();
    }

    public Mechanic createProduct(ArrayList<String> fieldValues){
        return new Mechanic(fieldValues.get(1),Integer.parseInt(fieldValues.get(0)));
    }

    public void editProduct(ArrayList<String> fieldValues, HashMap<String,ArrayList<CreatableObject>> composedArrayLists, CreatableObject creatableObject){
        Mechanic myMechanic = (Mechanic) creatableObject;
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<CreatableObject> carsToAdd = composedArrayLists.get("Автомобиль");
        for(CreatableObject creatableObject1: carsToAdd){
            cars.add((Car)creatableObject1);
        }
        myMechanic.setAllFields(fieldValues.get(1),Integer.parseInt(fieldValues.get(0)),cars);
    }
}
