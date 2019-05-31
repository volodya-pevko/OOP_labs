package main;

import templates.CreatableObject;

import java.util.ArrayList;

public class Data {
    private ArrayList<CreatableObject> creatableObjects = new ArrayList<>();

    ArrayList<CreatableObject> getCreatableObjects() {
        return creatableObjects;
    }

    void addObject(CreatableObject creatableObject){
        this.creatableObjects.add(creatableObject);
    }
}
