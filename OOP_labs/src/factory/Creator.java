package factory;

import templates.CreatableObject;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Creator {
    public CreatableObject createEmptyProduct(){
        return new CreatableObject();
    }

    public CreatableObject createProduct(ArrayList<String> fieldValues){
        return new CreatableObject(fieldValues.get(0));
    }

    public void editProduct(ArrayList<String> fieldValues, HashMap<String,ArrayList<CreatableObject>> composedArrayLists, CreatableObject creatableObject){
        creatableObject.setAllFields(fieldValues.get(0));
    }

    public void removeComposedObject(CreatableObject removeFrom, CreatableObject objectToRemove){
        removeFrom.removeObject(objectToRemove);
    }
}
