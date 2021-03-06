package task09;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvoker implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        CustomClassloader cloader = new CustomClassloader(proxy.getClass().getClassLoader());
        Class customClass = cloader.loadClass("task09.SomeClass");
        Method newMethod = customClass.getMethod(method.getName());
        return newMethod.invoke(customClass.newInstance(), args);
    }
}
