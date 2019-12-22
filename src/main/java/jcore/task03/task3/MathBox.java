package jcore.task03.task3;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class MathBox<T extends Number> extends ObjectBox<T> {
    private Set<Number> numberSet = new HashSet<>();

    public Set<Number> getNumberSet() {
        return numberSet;
    }

    public MathBox(Number[] arrNumbers) {
        for (Number number : arrNumbers) {
            numberSet.add(number);
        }
    }

    /**
     * возвращает сумму всех элементов numberSet
     *
     * @return Double value
     */
    public Double summator() {
        Double totalSum = 0d;
        for (Number number : numberSet) {
            totalSum += number.doubleValue();
        }
        return totalSum;
    }

    /**
     * делит каждый элемент numberSet на делитель
     *
     * @param divider - делитель
     */
    public void splitter(Number divider) {
        Set<Number> numberSetTmp = new HashSet<>();
        for (Iterator<Number> iterator = numberSet.iterator(); iterator.hasNext(); ) {
            Number element = iterator.next();
            numberSetTmp.add(element.doubleValue() / divider.doubleValue());
            iterator.remove();//удаляем элемент
        }
        numberSet.addAll(numberSetTmp);//при делении одинаковые результаты деления пропадут
    }

    /**
     * удаляет все элементы с типом Integer
     */
    public void deleteAllIntegerElements() {
        numberSet.removeIf(element -> element instanceof Integer);
    }

    @Override
    public void addObject(T object) {
        numberSet.add(object);
    }

    @Override
    public void deleteObject(T object) {
        numberSet.remove(object);
    }

    @Override
    public String dump() {
        StringBuilder b = new StringBuilder();
        for (Object setConvertStr : numberSet) {
            b.append(setConvertStr).append(", ");
        }
        b.deleteCharAt(b.lastIndexOf(","));
        return b.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return Objects.equals(numberSet, mathBox.numberSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberSet);
    }

    @Override
    public String toString() {
        return "MathBox" + numberSet;
    }
}
