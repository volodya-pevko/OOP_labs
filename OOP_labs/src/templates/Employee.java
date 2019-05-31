package templates;

import factory.Annotation;

@Annotation(name = "Сотрудник")
public class Employee extends CreatableObject {
    private int salary;

    public Employee(){}

    public Employee(String name, int salary){
        super(name);
        this.salary = salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
