public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String type() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
