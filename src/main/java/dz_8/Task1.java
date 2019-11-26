package dz_8;

import java.io.File;
import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) throws Exception {
        //самое важное
        String pomoika = "src/main/java/dz_8/pomoika";
        Arrays.stream(new File(pomoika).listFiles()).forEach(File::delete);
        //создаем объект и серриализуем
        MyXMLMapper mapper = new MyXMLMapper();
        SuperObject superMan = new SuperObject(SuperType.Human);
        superMan.setStatus(1);
        superMan.setName("Clark Kent");
        superMan.setPersists(new Double(123000000));
        try {
            mapper.writeValue(pomoika + File.separator + "result.txt", superMan);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //прочитаем сериализованный файл в новый объект
        try {
            mapper.readValue(pomoika + File.separator + "result.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
