package pixel;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getFileString() {
        return String.format("T | %s | %s", getStatusNumber(), getDescription());
    }
}
