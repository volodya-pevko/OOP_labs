package templates;

import factory.Annotation;

@Annotation(name = "Автомобиль")
public class Car extends CreatableObject {
    private boolean ifBroken;
    private int cost;

    public Car(){}

    public Car(String name, boolean ifBroken, int cost) {
        super(name);
        this.ifBroken = ifBroken;
        this.cost = cost;
    }

    public void setIfBroken(boolean ifBroken) {
        this.ifBroken = ifBroken;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
