package jcore.task09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomClassloader extends ClassLoader {
    public CustomClassloader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.endsWith("SomeClass")) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.endsWith("SomeClass")) {
            byte[] bytes = new byte[0];
            try {
                bytes = Files.readAllBytes(Paths.get("target/classes/dz_9/SomeClass.class"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return defineClass(name, bytes, 0, bytes.length);
        }
        return super.findClass(name);
    }
}
