package pacman;

public class Todo extends Task {
    Todo(String task) {
        super(task);
    }

    public String toFile() {
        return "T/" + super.toFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
