package dz_8;

import java.io.*;

public class MyXMLMapper implements IMyXMLMapper {

    /**
     * читает сериализованный объект и возвращает его экземпляр
     * @param t путь к файлу
     * @return
     */
    @Override
    public Object readValue(String t) throws Exception {
        String xml = readFile(t);
        new MyXMLParser().parse(xml);
        return null;
    }

    @Override
    public String writeValue(Object obj) throws Exception {
        return new MyXMLParser().parse(obj);
    }

    @Override
    public void writeValue(String file, Object obj) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, false)) {
            String xml = writeValue(obj);
            byte[] buffer = xml.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String filename) {
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
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return text;
    }
}
