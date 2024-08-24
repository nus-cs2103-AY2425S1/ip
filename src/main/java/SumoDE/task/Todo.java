package SumoDE;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String savedString() {
        return "T | " + super.savedString();
    }
}
