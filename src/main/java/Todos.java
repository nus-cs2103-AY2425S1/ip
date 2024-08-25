package main.java;

public class Todos extends Task {
    protected String by;

    public Todos(String description) {
        super(description);
    }

    public String getCategory() {
        return "[T]";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
