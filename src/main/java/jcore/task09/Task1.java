package jcore.task09;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Scanner;

public class Task1 {
    public static final String pathToJava = "src/main/java/dz_9/";

    public static void main(String[] args) {
        //отработала первичная реализация метода doWork
        Worker test = new SomeClass();
        test.doWork();
        //читаем код с консоли
        StringBuilder sbuilder = readFromConsole();
        try {
            //считанный код добавляются в тело метода public void doWork() в файл SomeClass.java
            String classText = FileUtils.readFile(pathToJava + "SomeClass.java");//как нормально?
            //редактируем класс
            editClass(sbuilder, classText);
            //перекомпилируем и кладем в таргет
            recompileNewClass();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        Worker someProxy = (Worker) Proxy.newProxyInstance(SomeClass.class.getClassLoader(),
                new Class[]{Worker.class},
                new CustomInvoker());
        someProxy.doWork();
    }

    private static void recompileNewClass() throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler.run(null, null, null, pathToJava + "SomeClass.java") != 0) {
            throw new RuntimeException("compilation failed");
        }
        FileUtils.moveFileToTarget(pathToJava + "SomeClass.class");
    }

    private static void editClass(StringBuilder sbuilder, String classText) throws IOException {
        String temp = "doWork()";
        String newClassText = classText.indexOf(temp) > 0 ?
                classText.substring(0, classText.indexOf(temp) + temp.length()) + "{" + sbuilder.toString() + "}}" :
                classText;
        FileUtils.writeFile(pathToJava + "SomeClass.java", newClassText);
    }

    private static StringBuilder readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sbuilder = new StringBuilder();
        System.out.print("Введите код:");
        while (true) {
            String input = scanner.nextLine();
            if ("".equals(input)) {
                System.out.println("Ввод завершен!");
                break;
            }
            sbuilder.append(input);
        }
        scanner.close();
        return sbuilder;
    }
}
