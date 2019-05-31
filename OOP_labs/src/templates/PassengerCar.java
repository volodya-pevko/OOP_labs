package templates;

import factory.Annotation;

@Annotation(name = "Легковой")
public class PassengerCar extends Car{
    private String bodyType;

    public PassengerCar(){}

    public PassengerCar(String name, boolean ifBroken, int cost, String bodyType){
        super(name, ifBroken, cost);
        this.bodyType = bodyType;
    }

    public void setAllFields(String name, boolean ifBroken, int cost, String bodyType){
        setName(name);
        setIfBroken(ifBroken);
        setCost(cost);
        setBodyType(bodyType);
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
