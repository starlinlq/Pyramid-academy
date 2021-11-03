package battleship;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //polymorphism

        List<Animal> list = new ArrayList<>();
        list.add(new Animal("cat", "male", 23));
        list.add(new Animal("dog", "female", 43));
        list.add(new Animal("bird", "male", 12));

        Comparator<Animal> comparator = new Comparator<Animal>() {
            @Override
            public int compare(Animal o1, Animal o2) {
                if(o1.getAge() > o1.getAge()){
                    return 1;
                } else if (o1.getAge() < o2.getAge()){
                    return -1;
                }
                return 0;
            }
        };

        Collections.sort(list, comparator);

        for(Animal a: list){
            System.out.println(a.getAge());
        }

        List<Integer> numbers = Arrays.asList(12, 2, 13, 4, 15, 6);

        numbers.sort((i1, i2) -> !i1.equals(i2) ? 0 : i2 - i1);

        System.out.println(numbers);
    }
}


 class Animal  {
    private String name;
    private String gender;
    private int age;
    //encapsulation


    Animal(){}
     //constructor overloading
    public Animal(String name, String gender, int age){
        this.name = name;
        this.gender = gender;
        this.age = age;
     }

    public void setName(String name){
        this.name = name;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

     public void isLoud(){
         System.out.println("grrrr");
     }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }




 }

//inheritance

class Cat extends Animal {
    Cat(){}

    Cat(String name, String gender, int age){
        super(name, gender, age);
    }
    //method overriding
    @Override
    public void isLoud(){
        System.out.println("meauhh");
    }
}

class Dog extends Animal{

    Dog(){};

    Dog(String name, String gender, int age){
        super(name, gender, age);
    }

    @Override
    public void isLoud(){
        System.out.println("hau hau hau");
    }
}
