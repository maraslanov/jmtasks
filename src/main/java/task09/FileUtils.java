package task09;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {
    public static String readFile(String filepath) throws IOException {
        String text = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            text = sb.toString();
        }
        return text;
    }

    public static void writeFile(String fileName, String content) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName, false)) {
            byte[] buffer = content.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);
        }
    }

    public static void moveFileToTarget(String fromPath) throws IOException {
        Path temp = Files.move(Paths.get(fromPath), Paths.get("target/classes/dz_9/SomeClass.class"), StandardCopyOption.REPLACE_EXISTING);
        if (temp == null) {
            throw new IOException("не удалось переместить класс в нужную директорию");
        }
    }
}
