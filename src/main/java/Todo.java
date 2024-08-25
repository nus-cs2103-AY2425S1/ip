public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSave() {
        return "T" + super.toSave();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

