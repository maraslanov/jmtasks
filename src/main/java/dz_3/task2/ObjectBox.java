package dz_3.task2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ObjectBox implements IObjectBox {
    private Set set = new HashSet();//вставка и удаление О(1)

    public Set getSet() {
        return set;
    }

    /**
     * метод, добавляющий объект в коллекцию
     *
     * @param object
     */
    public void addObject(Object object) {
        set.add(object);
    }

    /**
     * метод, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
     *
     * @param object
     */
    public void deleteObject(Object object) {
        if (set.contains(object)) {//для Set не нужно проверять
            set.remove(object);
        }
    }

    /**
     * метод dump, выводящий содержимое коллекции в строку
     *
     * @return String
     */
    public String dump() {
        StringBuilder b = new StringBuilder();
        for (Object setConvertStr : set) {
            b.append(setConvertStr).append(", ");
        }
        b.deleteCharAt(b.lastIndexOf(","));
        return b.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox objectBox = (ObjectBox) o;
        return Objects.equals(set, objectBox.set);
    }

    @Override
    public int hashCode() {
        return Objects.hash(set);
    }

    @Override
    public String toString() {
        return "ObjectBox{" + dump();
    }
}
