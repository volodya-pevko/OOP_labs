package factory;

import templates.CreatableObject;
import templates.Truck;

import java.util.ArrayList;
import java.util.HashMap;

@Annotation(name = "Грузовик")
public class TruckCreator extends Creator {
    public Truck createEmptyProduct(){
        return new Truck();
    }

    public Truck createProduct(ArrayList<String> fieldValues){
        return new Truck(fieldValues.get(3),Boolean.parseBoolean(fieldValues.get(1)),Integer.parseInt(fieldValues.get(2)),Integer.parseInt(fieldValues.get(0)));
    }

    public void editProduct(ArrayList<String> fieldValues, HashMap<String,ArrayList<CreatableObject>> composedArrayLists, CreatableObject creatableObject){
        Truck myTruck = (Truck) creatableObject;
        myTruck.setAllFields(fieldValues.get(3),Boolean.parseBoolean(fieldValues.get(1)),Integer.parseInt(fieldValues.get(2)),Integer.parseInt(fieldValues.get(0)));
    }
}