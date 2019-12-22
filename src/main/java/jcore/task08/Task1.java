package jcore.task08;

import java.io.File;
import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) throws Exception {
        //самое важное
        String pomoika = "src/main/java/dz_8/pomoika";
        Arrays.stream(new File(pomoika).listFiles()).forEach(File::delete);
        //создаем объект и серриализуем
        MyXMLMapper mapper = new MyXMLMapper();
        SuperObject superMan = new SuperObject("Clark Kent", SuperType.Human, null, 1);
        superMan.setSecret(new SuperObject("SuperMan", SuperType.Alien, null, 0));
        System.out.println("Объект до серриализации:");
        System.out.println(superMan.toString());
        try {
            mapper.serialize(superMan, pomoika + File.separator + "result.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //прочитаем сериализованный файл в новый объект
        try {
            System.out.println("Объект после серриализации:");
            System.out.println(mapper.deSerialize(pomoika + File.separator + "result.txt").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
