package templates;

import factory.Annotation;

@Annotation(name = "Грузовик")
public class Truck extends Car{
    private int capacity;

    public Truck(){}

    public Truck(String name, boolean ifBroken, int cost, int capacity){
        super(name, ifBroken, cost);
        this.capacity = capacity;
    }

    public void setAllFields(String name, boolean ifBroken, int cost, int capacity){
        setName(name);
        setIfBroken(ifBroken);
        setCost(cost);
        setCapacity(capacity);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
