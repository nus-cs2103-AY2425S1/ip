public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String write() {
        return "T" + super.write();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
