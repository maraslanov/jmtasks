package jcore.task08;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MyXMLParser<T> {
    private static String excText = "MyXMLParser parse exception: ";
    private String pathPackage = this.getClass().getPackage().getName();

    /**
     * метод для преобразование объекта в строку
     */
    public String parse(Object obj) throws Exception {
        if (obj == null)
            throw new Exception(excText + "Object is null");
        String className = String.valueOf(obj.getClass().getSimpleName());
        //превращаем объект в плоскую строку
        StringBuilder xml = new StringBuilder().append(getOpenTag(className));
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            //проверяем можно ли сериализовать поле
            if (!Modifier.isTransient(field.getModifiers())) {
                //доступ к прайвет полю
                if (!field.isAccessible())
                    field.setAccessible(true);
                //проверяем ссылочное поле
                if (field.getType().getName().startsWith(pathPackage) && !field.getType().isEnum()) {//енамы это "строки"
                    if (field.get(obj) != null) {
                        xml.append(parse(field.get(obj)));
                    }
                } else {
                    String tag = field.getName();
                    xml.append(getOpenTag(tag));//начали описывать поле
                    if (field.get(obj) != null) {
                        xml.append(field.get(obj));//записали значение
                    }
                    xml.append(getCloseTag(tag));//закончили описывать поле
                }
            }
        }
        xml.append(getCloseTag(className));
        return xml.toString();
    }

    public Object parse(String xml) throws Exception {
        //получили название класса из файла
        String classTag = readFirstTag(xml);
        Class<?> aClass = Class.forName(pathPackage + "." + classTag);
        //создаем новый экземпляр класса с данными из файла
        SuperObject superObject = null;
        if (aClass != null) {
            superObject = (SuperObject) aClass.newInstance();
            xml = removeFirstTag(xml, classTag.length());
            //выдираем подстроки и устанавливаем данные из них
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                //доступ к прайвет полю
                if (!field.isAccessible())
                    field.setAccessible(true);
                //вложенный класс повторно парсим
                if (field.getType().getName().equals(SuperObject.class.getName())) {
                    if (xml.indexOf(field.getType().getSimpleName()) > 0) {
                        String podstrokaAfterOpenTag = xml.substring(xml.indexOf(field.getType().getSimpleName()) - 1);//...</field>
                        //String znachenie = podstrokaAfterOpenTag.substring(0, podstrokaAfterOpenTag.indexOf(field.getType().getSimpleName()) - 2);
                        //String podstroka = podstrokaAfterOpenTag.substring(0, xml.indexOf(classTag) - 1, xml.lastIndexOf(classTag) + 1);
                        field.set(superObject, parse(podstrokaAfterOpenTag));
                    }
                } else {
                    //обычные поля достаем без рекурсии
                    if (xml.indexOf(field.getName()) > 0) {
                        String podstrokaAfterOpenTag = xml.substring(xml.indexOf(field.getName()) + field.getName().length() + 1);//...</field>
                        String znachenie = podstrokaAfterOpenTag.substring(0, podstrokaAfterOpenTag.indexOf(field.getName()) - 2);
                        //енамы не совсем строки:
                        field.set(superObject, field.getType().isEnum() ? Enum.valueOf((Class<Enum>) field.getType(), znachenie) : HelpUtils.castText(znachenie));
                    }
                }
            /*for (Method method : aClass.getDeclaredMethods()) {
                //интересуют только сетеры
                if (method.getName().startsWith("set")) {
                    //вложенный класс повторно парсим
                    if (method.getName().equals("setSecret")) {
                        //if ("setPassport".equals(method.getName())) {
                        String podstroka = xml.substring(xml.indexOf(classTag) - 1, xml.lastIndexOf(classTag + 1));
                        method.invoke(superObject, parse(podstroka));
                    } else {
                        //обычные поля достаем без рекурсии

                    }
                }*/
            }
        }
        return superObject;
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
