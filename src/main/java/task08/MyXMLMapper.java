package task08;

/**
 * класс для сериализации и десериализации
 */
public class MyXMLMapper implements ISerialization {

    /**
     * читает сериализованный объект и возвращает его экземпляр
     *
     * @param t путь к файлу
     * @return
     */
    public Object deSerialize(String t) throws Exception {
        String xml = HelpUtils.readFile(t);
        return new MyXMLParser().parse(xml);
    }

    /**
     * сериализует объект в файл
     *
     * @param obj  объект
     * @param file путь к файлу
     * @throws Exception
     */
    @Override
    public void serialize(Object obj, String file) throws Exception {
        String xml = serialize(obj);
        HelpUtils.writeFile(file, xml);
    }

    public String serialize(Object obj) throws Exception {
        return new MyXMLParser().parse(obj);
    }
}
