public class Todo extends Task{
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveRepresentation() {
        return "T | " + super.toSaveRepresentation();
    }
}
