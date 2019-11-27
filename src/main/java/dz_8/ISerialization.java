package dz_8;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface ISerialization {
    /**
     * Сериализация объекта в файл
     */
    void serialize(Object object, String file) throws Exception;

    /**
     * Десериализация объекта из файла
     */
    Object deSerialize(String file) throws Exception;
}