package task08;

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