package jcore.task12.HeapTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * класс для тестирования OutOfMemoryError c пометкой Java Heap Space
 * необходимо запустить с опцией ограничивающей размер хипа: например -XX: -Xmx256m
 */
public class task1 {
    private static final int LOOP_COUNT  = 100_000_000;

    public static void main(String[] args) throws InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < LOOP_COUNT; i++) {
            if (i % 100_000 == 0) {
                TimeUnit.SECONDS.sleep(1);
            }
            String str = "static" + random.nextInt();
            stringBuilder.append(str);
        }
    }
}
