package task02.task3;

public class BubbleSort {
    /**
     * метод возвращает отсортированный методом пузырька массив класса Person и выводит в консоль время выполнения метода
     * @param array неотсортированный массив
     * @return отсортированный массив
     */
    public static Person[] sort(Person[] array) {
        long startTime = System.currentTimeMillis();
        boolean sorted = false;
        Person temp;
        int lastUnsorted = array.length - 1;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < lastUnsorted; i++) {
                if (array[i].compareTo(array[i + 1]) < 0) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    sorted = false;
                }
            }
            lastUnsorted--;
        }
        System.out.println("Bubble sort finished by " + String.valueOf(System.currentTimeMillis() - startTime));
        return array;
    }
}
