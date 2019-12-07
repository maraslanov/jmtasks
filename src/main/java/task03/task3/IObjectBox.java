package task03.task3;

public interface IObjectBox<T> {
    public void addObject(T object);
    public void deleteObject(T object);
    public String dump();
}
