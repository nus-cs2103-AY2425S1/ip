public class ToDo extends Task {
    public ToDo(String description, int type) {
        super(description, type);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
