package task08;

import java.io.*;

/**
 * класс приводит строку к объекту нужного типа
 */
public class HelpUtils {
    /**
     * приводит строку к объекту
     * @param str
     * @return
     */
    public static Object castText(String str) {
        Object object = str;
        if ("false".equals(str.toLowerCase()) || "true".equals(str.toLowerCase())) {
            return Boolean.parseBoolean(str);
        }
        Number number = getValue(str);
        if (number != null) {
            return number;
        }
        return object;
    }

    /**
     * проверяет является ли строка числом
     * @param number
     * @return
     */
    private static boolean isNumber(String number) {
        return number != null && number.matches("[+-]?\\d+[.]?\\d+");
    }

    /**
     * возвращает число нужного типа
     * @param number
     * @param <T>
     * @return
     */
    private static <T extends Number> T getValue(String number) {
        T value = null;
        if (isNumber(number)) {
            if (number.indexOf('.') != -1) {
                value = (T) new Double(number);
            } else {
                Long lValue = new Long(number);
                value = lValue == lValue.intValue() ? (T) new Integer(number) : (T) lValue;
            }
        }
        return value;
    }

    public static String readFile(String filename) throws Exception {
        String text = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            text = sb.toString();
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
        return text;
    }

    public static void writeFile(String file, String xml){
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, false)) {
            byte[] buffer = xml.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
