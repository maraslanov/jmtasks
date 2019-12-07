package task08;

public class SuperObject {
    private String name;
    private SuperType type;
    private SuperObject secret;
    private transient int status;

    public SuperObject() {
    }

    public SuperObject(String name, SuperType type, SuperObject persists, int status) {
        this.name = name;
        this.type = type;
        this.secret = persists;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SuperObject getSecret() {
        return secret;
    }

    public void setSecret(SuperObject secret) {
        this.secret = secret;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Имя: " + this.name +
                " type-" + this.type +
                " status-" + this.status +
                " secret: " + (getSecret() != null ? "[" + getSecret().toString() + "]" : "не известен");

    }
}
