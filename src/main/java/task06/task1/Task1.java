package task06.task1;

import java.io.*;
import java.util.*;

public class Task1 {
    public static void main(String[] args) {
        //самое важное
        String pomoika = "src/main/java/dz_6/pomoika";
        Arrays.stream(new File(pomoika).listFiles()).forEach(File::delete);
        //читаем из файла в строчку:
        String alltext = readFile("src/main/resources/forread.txt");
        //удаляем лишние пробелы и спец символы
        alltext = alltext.replaceAll("\n|\r\n", " ");
        alltext = deleteLetters(alltext);
        //в множество
        Set<String> items = new TreeSet<>(Arrays.asList(alltext.trim().toLowerCase().split(" ")));
        //приведение к регистру для быстрой сортировки
        writeFile(pomoika + File.separator + "forresult.txt", items.toString());
    }

    /**
     * оставляет в строке только буквы и цыфры и пробелы не больше 1 подряд идущего
     *
     * @param s
     * @return результат форматирвоания строки
     */
    private static String deleteLetters(String s) {
        StringBuilder sb = new StringBuilder();
        if (Character.isLetterOrDigit(s.charAt(0)))
            sb.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i)) || (s.charAt(i) == ' ' && sb.charAt(sb.length() - 1) != ' ') || (s.charAt(i) == '-' && Character.isLetterOrDigit(s.charAt(i - 1)))) {
                char c = s.charAt(i);
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * читает файл из ресурсов
     *
     * @param filename
     * @return
     */
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

    /**
     * записывает в файл строку
     *
     * @param fileName
     * @param content
     */
    public static void writeFile(String fileName, String content) {
        //try(FileWriter writer = new FileWriter(fileName, false))
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName, false)) {
            byte[] buffer = content.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
