package dz_2.task2;

import java.util.Random;

public class Task2 {
    public static void main(String[] args) {
        int n = 100;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            try {
                int k = -2 + random.nextInt(100);
                if (k < 0) {
                    throw new Exception("сгенерировалось отрицательное число, операция получения корня вернет NaN");
                }
                double sqrt = Math.sqrt(k);
                if (Math.pow((int) sqrt, 2) == k) {
                    System.out.println("k=" + k + " sqrt(k)=" + sqrt);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
