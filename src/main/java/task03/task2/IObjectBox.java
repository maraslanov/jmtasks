package task03.task2;

public interface IObjectBox<T> {
    public void addObject(T object);
    public void deleteObject(T object);
    public String dump();
}
