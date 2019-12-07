package task02.task3;

public class ShellSort {
    /**
     * метод возвращает отсортированный алгоритмом Шелла массив класса Person и выводит в консоль время выполнения метода
     * @param array неотсортированный массив
     * @return отсортированный массив
     */
    public static Person[] sort(Person[] array) {
        long startTime = System.currentTimeMillis();
        int dif = array.length / 2;
        while (dif >= 1) {
            for (int right = 0; right < array.length; right++) {
                for (int c = right - dif; c >= 0; c -= dif) {
                    if (array[c].compareTo(array[c + dif]) < 0) {
                        Person tmp = array[c];
                        array[c] = array[c + dif];
                        array[c + dif] = tmp;
                    }
                }
            }
            dif = dif / 2;
        }
        System.out.println("Shell sort finished by " + String.valueOf(System.currentTimeMillis() - startTime));
        return array;
    }
}

