package task12.metaspacetest;

import java.util.ArrayList;
import java.util.List;

/**
 * класс для тестирования OutOfMemoryError в Metaspace /Permanent Generation
 * -XX: MaxMetaspaceSize=9m
 */
public class task2 {
    private static final int LOOP_COUNT = 100_000_000;

    public static void main(String[] args) throws InterruptedException {
        List<Object> objectList = new ArrayList<>();
        demoOutOfMemoryError();
        for (int i = 0; i < LOOP_COUNT; i++) {
            //objectList.add(new Object());
            demoOutOfMemoryError();
        }
    }

    static void demoOutOfMemoryError() throws InterruptedException {
        Thread.sleep(10_000);
        int multiplier = 100;
        for (int i = 1; i < 50; i++) {
            Thread.sleep(1);
            System.out.println("Round " + i + " Free Memory: " + Runtime.getRuntime().freeMemory());
            int[] myIntList = new int[multiplier];
            for (int j = i; j > 1; j--) {
                myIntList[j] = i;
            }
            multiplier = multiplier * 10;
        }
    }
}
