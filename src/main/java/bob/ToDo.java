package bob;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getSaveFormat() {
        return "T | " + super.getSaveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
