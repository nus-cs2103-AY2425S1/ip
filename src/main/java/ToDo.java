public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getFormatted() {
        return "T|" + super.getStatusIcon() + "|" + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
