package dz_8;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MyXMLParser<T> {
    private static String excText = "MyXMLParser parse exception: ";

    /**
     * метод для преобразование объекта в строку
     */
    public String parse(Object obj) throws Exception {
        if (obj == null)
            throw new Exception(excText + "Object is null");
        String className = String.valueOf(obj.getClass().getName());
        //превращаем объект в плоскую строку
        StringBuilder xml = new StringBuilder().append(getOpenTag(className));
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            //проверяем можно ли сериализовать поле
            if (!Modifier.isTransient(field.getModifiers())) {
                field.setAccessible(true);//доступ к прайвет полю выдан
                String tag = field.getName();
                xml.append(getOpenTag(tag));//начали описывать поле
                if (field.get(obj) != null) {
                    //todo если ссылочный тип - надо отпарсить его сначала
                    xml.append(field.get(obj));//записали значение
                }
                xml.append(getCloseTag(tag));//закончили описывать поле
            }
        }
        xml.append(getCloseTag(className));
        return xml.toString();
    }

    public T parse(String xml) throws Exception {
        String classString = readFirstTag(xml);
        xml = removeFirstTag(xml, classString.length());
        Class<?> serClass = Class.forName(classString);
        if (serClass != null) {

        }
        return null;
    }

    public String readFirstTag(String str) {
        if (str != null && str.length() > 0) {
            return str.substring(1, str.indexOf(">"));
        } else return str;
    }

    public String removeFirstTag(String str, int length) {
        if (str != null && str.length() > 0) {
            str = str.substring(length + 2);
            return str.substring(0, str.lastIndexOf("<"));
        } else return str;
    }

    public String getOpenTag(String str) {
        return "<" + str + ">";
    }

    public String getCloseTag(String str) {
        return "</" + str + ">";
    }
}
