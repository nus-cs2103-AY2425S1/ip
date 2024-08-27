public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "ToDo | " + (isDone ? "Done" : "Not Done") + " | " + description;
    }
}

