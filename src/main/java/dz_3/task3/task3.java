package dz_3.task3;

public class task3 {
    public static void main(String[] args) {
        Number[] arrNumbers = new Number[]{0, 10, 20L, 20, 20D, 20.7};
        MathBox mathBox = new MathBox(arrNumbers);
        System.out.println(mathBox.toString());

        System.out.println("Сумма элементов");
        System.out.println(mathBox.summator());

        System.out.println("коллекция с типами до удаления Integer");
        for (Object number : mathBox.getSet()) {
            System.out.println(number.getClass() + " " + String.valueOf(number));
        }

        System.out.println("коллекция с типами после удаления Integer");
        mathBox.deleteAllIntegerElements();
        for (Object number : mathBox.getSet()) {
            System.out.println(number.getClass() + " " + String.valueOf(number));
        }

        int delitel = 2;
        System.out.println("Деление всех элементов на "+delitel);
        mathBox.splitter(delitel);
        System.out.println(mathBox.toString());

        System.out.println("Добавлен элемент Long 123 ");
        mathBox.addObject(new Long(123));
        System.out.println(mathBox.toString());

        System.out.println("Удален элемент Long 123 ");
        mathBox.deleteObject(123L);
        System.out.println(mathBox.toString());
    }
}
