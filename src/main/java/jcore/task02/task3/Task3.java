package jcore.task02.task3;

import java.util.Random;

public class Task3 {
    public static void main(String[] args) {
        int n = 10000;
        Person[] array = new Person[n];
        //генерация массива
        for (int i = 0; i < n; i++) {
            array[i] = new Person(1+ new Random().nextInt(100), getRandomSex(), getRandomName());
            //System.out.println(array[i].toString());
        }
        //сортировка1 и вывод
        Person[] array2 = BubbleSort.sort(array);
        printArray(n, array2);
        //сортировка2 и вывод
        Person[] array3 = ShellSort.sort(array);
        printArray(n, array3);
    }

    private static void printArray(int n, Person[] array3) {
        StringBuilder b = new StringBuilder();
        b.append("{");
        for (int i = 0; i < n; i++) {
            b.append(array3[i].toString() + ", ");
        }
        b.deleteCharAt(b.length()-1);
        b.append("}");
        System.out.println(b.toString());
    }

    /**
     * метод возвращает случайный элемент перечисления Sex
     * @return MAN or WOMAN
     */
    public static Sex getRandomSex() {
        int temp = new Random().nextInt(2);
        if (temp == 1) {
            return Sex.MAN;
        }
        return Sex.WOMAN;
    }

    static char[] chars = {'q', 'w', 'e', 'r', 't', 'z', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k',
            'l', 'y', 'x', 'c', 'v', 'b', 'n', 'm', 'Q', 'W', 'E', 'R', 'T', 'Z', 'U', 'I', 'O', 'P', 'A', 'S', 'D',
            'F', 'G', 'H', 'J', 'K', 'L', 'Y', 'X', 'C', 'V', 'B', 'N', 'M'};

    /**
     * метод возвращает случайную строку заданной длины состояющую из символов английского алфавита
     * @param length длина строки, целое число
     */
    private static String randomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        //заданное количество раз генерируется символ из статического массива
        for (int i = 0; i < length; i++) {
            stringBuilder.append(chars[new Random().nextInt(chars.length)]);
        }
        return stringBuilder.toString();
    }

    /**
     * метод возвращает случайную строку случайной длины состояющую из символов английского алфавита
     */
    public static String getRandomName() {
        Random rndLength = new Random();
        return randomString(1+rndLength.nextInt(15));
    }
}
