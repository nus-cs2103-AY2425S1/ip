package milo.clients;

public class Client {

    private final String name;
    private final String condition;
    public Client(String name, String condition) {
        this.name = name;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return this.name + ", condition: " + this.condition;
    }

    public String toTextString() {
        return name + " | " + condition;
    }
}
