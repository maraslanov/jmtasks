package dz_7;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Task1 {
    public static ConcurrentHashMap<Integer, BigInteger> cache = new ConcurrentHashMap<>();//1)почему в статике нельзя параметризировано в new?

    private static BigInteger calculateFactorial(Integer n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        BigInteger result = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++) {
            if (cache.containsKey(i)) {
                result = cache.get(i);
            } else {
                result = result.multiply(BigInteger.valueOf(i));
                cache.put(i, result);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //сгенерировать массив из n чисел
        int n = 100;//пусть состоит из 100 элементов
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = new Random().nextInt(1000);
        }
        BigInteger[] factorialArr = new BigInteger[n];
        //выделим n потоков на вычисление факториала
        for (int i = 0; i < n; i++) {
            //Создание потока
            System.out.println("начато вычисление факториала " + i + "-го элемента");
            int finalI = i;
            new Thread(new Runnable() {
                public void run() {
                    //вычисляем факториал
                    if (cache.containsKey(finalI)) {
                        factorialArr[0] = cache.get(finalI);
                    } else {
                        factorialArr[finalI] = calculateFactorial(finalI);
                    }
                }
            }).start();    //Запуск потока
            System.out.println("вычисления факториала " + i + "-го элемента заверешено");
        }
        //2)надо проверить что все потоки завершились?
        for (int i = 0; i < n; i++) {
            System.out.println("i=" + i + " factorial=" + factorialArr[i]);
        }
    }
}
