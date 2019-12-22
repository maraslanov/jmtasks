package jcore.task03.task2;

import java.util.ArrayList;

public class Task2 {
    public static void main(String[] args) {
        ObjectBox objectBox = new ObjectBox();
        objectBox.addObject("hoba");
        objectBox.addObject(1L);
        objectBox.addObject(new ArrayList<>());

        System.out.println("Начальная коллекция:");
        System.out.println(objectBox.dump());

        System.out.println("удален элемент 1L");
        objectBox.deleteObject(1L);
        System.out.println(objectBox.dump());

        System.out.println("удален элемент - пустой массив");
        objectBox.deleteObject(new ArrayList<>());
        System.out.println(objectBox.dump());
    }
}
