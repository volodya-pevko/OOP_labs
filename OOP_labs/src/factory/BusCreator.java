package factory;

import templates.Bus;
import templates.CreatableObject;

import java.util.ArrayList;
import java.util.HashMap;

@Annotation(name = "Автобус")
public class BusCreator extends Creator{
    public Bus createEmptyProduct(){
        return new Bus();
    }

    public Bus createProduct(ArrayList<String> fieldValues){
        return new Bus(fieldValues.get(3),Boolean.parseBoolean(fieldValues.get(1)),Integer.parseInt(fieldValues.get(2)),Integer.parseInt(fieldValues.get(0)));
    }

    public void editProduct(ArrayList<String> fieldValues, HashMap<String,ArrayList<CreatableObject>> composedArrayLists, CreatableObject creatableObject){
        Bus myBus = (Bus) creatableObject;
        myBus.setAllFields(fieldValues.get(3),Boolean.parseBoolean(fieldValues.get(1)),Integer.parseInt(fieldValues.get(2)),Integer.parseInt(fieldValues.get(0)));
    }
}
