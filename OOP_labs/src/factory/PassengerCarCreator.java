package factory;

import templates.CreatableObject;
import templates.PassengerCar;

import java.util.ArrayList;
import java.util.HashMap;

@Annotation(name = "Легковой")
public class PassengerCarCreator extends Creator {
    public PassengerCar createEmptyProduct(){
        return new PassengerCar();
    }

    public PassengerCar createProduct(ArrayList<String> fieldValues){
        return new PassengerCar(fieldValues.get(3),Boolean.parseBoolean(fieldValues.get(1)),Integer.parseInt(fieldValues.get(2)),fieldValues.get(0));
    }

    public void editProduct(ArrayList<String> fieldValues, HashMap<String,ArrayList<CreatableObject>> composedArrayLists, CreatableObject creatableObject){
        PassengerCar myCar = (PassengerCar) creatableObject;
        myCar.setAllFields(fieldValues.get(3),Boolean.parseBoolean(fieldValues.get(1)),Integer.parseInt(fieldValues.get(2)),fieldValues.get(0));
    }
}
