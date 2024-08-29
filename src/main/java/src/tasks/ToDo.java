package src.tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toPrettierString() {

        return "T" + super.toPrettierString();
    }


    @Override
    public String toString() {

        return "[T]" + super.toString();
    }
}
