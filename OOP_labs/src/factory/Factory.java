package factory;

import templates.CreatableObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Factory
{
    private static final HashMap<String, Class<? extends Creator>> annotations = new HashMap<>();
    public Factory() {
        ArrayList<Class<? extends Creator>> creatorsList = new ArrayList<>();
        creatorsList.add(CarParkCreator.class);
        creatorsList.add(DriverCreator.class);
        creatorsList.add(MechanicCreator.class);
        creatorsList.add(PassengerCarCreator.class);
        creatorsList.add(BusCreator.class);
        creatorsList.add(TruckCreator.class);
        for (Class<? extends Creator> creator : creatorsList)
        {
            if (creator.isAnnotationPresent(Annotation.class))
            {
                Annotation annotation = creator.getAnnotation(Annotation.class);
                annotations.put(annotation.name(), creator);
            }
        }
    }

    public CreatableObject firstObjectCreation(String annotationName)
    {
        try {
            Creator creator = annotations.get(annotationName).newInstance();
            return creator.createEmptyProduct();
        } catch (InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
        return null;
    }

    public CreatableObject finalObjectCreation(String annotationName, ArrayList<String> fieldValues)
    {
        try {
            Creator creator = annotations.get(annotationName).newInstance();
            return creator.createProduct(fieldValues);
        } catch (InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
        return null;
    }

    public void editingObject(String annotationName, ArrayList<String> fieldValues, HashMap<String,ArrayList<CreatableObject>> composedArrayLists, CreatableObject creatableObject)
    {
        try {
            Creator creator = annotations.get(annotationName).newInstance();
            creator.editProduct(fieldValues,composedArrayLists,creatableObject);
        } catch (InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    public void removeComposedObject(CreatableObject removeFrom, CreatableObject objectToRemove){
        try {
            Creator creator = annotations.get(removeFrom.getClass().getAnnotation(Annotation.class).name()).newInstance();
            creator.removeComposedObject(removeFrom,objectToRemove);
        }catch (InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
    }
}
