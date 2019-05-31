package templates;

public class CreatableObject {
    protected String name;

    public CreatableObject(){}

    public CreatableObject(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAllFields(String name){
        this.name = name;
    }

    public void removeObject(CreatableObject objectToRemove){

    }
}
