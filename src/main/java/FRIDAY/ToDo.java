package FRIDAY;

public class ToDo extends Task {
    public ToDo(String description, int type) {
        super(description, type);
    }

    public String storageDisplay() {
        return "T" + super.storageDisplay();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
