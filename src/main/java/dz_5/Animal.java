package dz_5;

import java.util.Objects;
import java.util.UUID;

public class Animal implements Comparable<Animal> {
    private final String uniqueNumber = UUID.randomUUID().toString();
    private String name;
    private Person person;
    private int weight;

    public Animal(String name, Person person, int weight) {
        this.name = name;
        this.person = person;
        this.weight = weight;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(uniqueNumber, animal.uniqueNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueNumber);
    }

    @Override
    public String toString() {
        return "кличка:" + getName() + ", хозяин:" + getPerson().toString() + ", вес:" + weight + " фунтов";
    }

    @Override
    public int compareTo(Animal o) {
        return weight == o.getWeight() && getName().equalsIgnoreCase(o.getName()) && person.equals(o.getPerson()) ? 0 : 1;
    }
}