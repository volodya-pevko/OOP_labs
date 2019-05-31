package templates;

import factory.Annotation;

@Annotation(name = "Автобус")
public class Bus extends Car{
    private int passengersNum;

    public Bus(){}

    public Bus(String name, boolean ifBroken, int cost, int passengersNum){
        super(name, ifBroken, cost);
        this.passengersNum = passengersNum;
    }

    public void setAllFields(String name, boolean ifBroken, int cost, int passengersNum){
        setName(name);
        setIfBroken(ifBroken);
        setCost(cost);
        setPassengersNum(passengersNum);
    }

    public void removeObject(CreatableObject objectToRemove){

    }

    public void setPassengersNum(int passengersNum) {
        this.passengersNum = passengersNum;
    }
}
