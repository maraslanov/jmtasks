package dz_8;

public class SuperObject<T> {
    private String name;
    private final SuperType type;
    public Double persists;
    private transient int status;

    public SuperObject(SuperType type) {
        this.type = type;
    }

    public SuperObject(String name, SuperType type, Double persists, int status) {
        this.name = name;
        this.type = type;
        this.persists = persists;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SuperType getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Double getPersists() {
        return persists;
    }

    public void setPersists(Double persists) {
        this.persists = persists;
    }
}
