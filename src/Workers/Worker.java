//Создать класс "Сотрудник" с полями: ФИО, зарплата, возраст;
//Конструктор класса должен заполнять эти поля при создании объекта;
//Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;
//*** Продумать конструктор таким образом, чтобы при создании каждому сотруднику присваивался личный уникальный идентификационный порядковый номер

package Workers;

public class Worker {

    private static int UID = 1;

    private String name;
    private int age;
    private double salary;
    private int id;

    Worker(String name, int age, double salary){
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.id = UID;
        UID++;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }
    public int getId(){
        return this.id;
    }

    public double getSalary(){
        return this.salary;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }
}
