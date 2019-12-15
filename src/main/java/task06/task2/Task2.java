package task06.task2;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Task2 {
    public static void main(String[] args) {
        //самое важное
        String pomoika = "src/main/java/dz_6/pomoika";
        Arrays.stream(new File(pomoika).listFiles()).forEach(File::delete);

        int n = 1 + new Random().nextInt(5);//кол-во файлов, скажем, до 5 штук
        int probability = 1;//
        int size = 1024;//байт
        String path = pomoika;

        getFiles(n, probability, size, path);
    }

    private static void getFiles(int n, int probability, int size, String path) {
        //сгенерировать массив из n4 слов
        int n4 = 1 + new Random().nextInt(1000);
        String[] array = new String[n4];
        for (int i = 0; i < n4; i++) {
            array[i] = getRandomName();
        }
        //генерируем абазацы
        StringBuilder sb = new StringBuilder();
        for (int i1 = 0; i1 < n; i1++) {
            try (FileOutputStream out = new FileOutputStream(path + File.separator + "file" + i1 + ".txt");
                 BufferedOutputStream bos = new BufferedOutputStream(out)) {
                int maxCountBytesFile = size;
                probability = new Random().nextInt(n4);
                byte[] paragraph = generateAbzac(probability, array).getBytes();
                //записываем абзацы в файл
                while (maxCountBytesFile > 0) {
                    if (paragraph.length >= maxCountBytesFile) {
                        //сколько влезает
                        bos.write(paragraph, 0, maxCountBytesFile);
                        maxCountBytesFile = 0;
                    } else {
                        //целиком абзац и снова генерируем, чтобы добить файл
                        bos.write(paragraph, 0, paragraph.length);
                        maxCountBytesFile -= paragraph.length;
                        paragraph = generateAbzac(probability, array).getBytes();
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * создает обзац в виде строки, начинается с отступа, заканчивается переносом на новую строку
     *
     * @param probability
     * @param array
     * @return
     */
    private static String generateAbzac(int probability, String[] array) {
        //для каждого абазаца строка из n3 предложений
        int n3 = 1 + new Random().nextInt(20);
        StringBuilder sbAbzac = new StringBuilder();
        sbAbzac.append("\t");
        for (int i2 = 0; i2 < n3; i2++) {
            //создаем предложение и добавляем в абзац
            String predlojenie = generateSentence(probability, array);
            sbAbzac.append(predlojenie).append(charsForEnd[new Random().nextInt(charsForEnd.length)]).append(" ");
        }
        sbAbzac.deleteCharAt(sbAbzac.length() - 1);
        sbAbzac.append("\r\n");
        return sbAbzac.toString();
    }

    /**
     * генерирует строку-предложение из слова массива
     *
     * @param array
     * @return
     */
    private static String generateSentence(int probability, String[] array) {
        StringBuilder sbPredlojenie = new StringBuilder();
        int n1 = 1 + new Random().nextInt(15);
        for (int i3 = 0; i3 < n1; i3++) {
            int index = new Random().nextInt(probability);
            sbPredlojenie.append(array[index] + " ");
        }
        sbPredlojenie.deleteCharAt(sbPredlojenie.length() - 1);
        String predlojenie = sbPredlojenie.toString();
        predlojenie = predlojenie.substring(0, 1).toUpperCase() + predlojenie.substring(1);
        return predlojenie;
    }

    /**
     * метод возвращает случайную строку заданной длины состояющую из символов английского алфавита
     *
     * @param length длина строки, целое число
     */
    private static String randomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        //заданное количество раз генерируется символ из статического массива
        for (int i = 0; i < length; i++) {
            stringBuilder.append(chars[new Random().nextInt(chars.length)]);
        }
        return stringBuilder.toString();
    }

    /**
     * метод возвращает случайную строку случайной длины состояющую из символов английского алфавита случайной длиной до 15 букв
     */
    public static String getRandomName() {
        Random rndLength = new Random();
        return randomString(1 + rndLength.nextInt(15));
    }

    static char[] chars = {'q', 'w', 'e', 'r', 't', 'z', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k',
            'l', 'y', 'x', 'c', 'v', 'b', 'n', 'm', 'Q', 'W', 'E', 'R', 'T', 'Z', 'U', 'I', 'O', 'P', 'A', 'S', 'D',
            'F', 'G', 'H', 'J', 'K', 'L', 'Y', 'X', 'C', 'V', 'B', 'N', 'M'};
    static char[] charsForEnd = {'.', '!', '?'};
}
