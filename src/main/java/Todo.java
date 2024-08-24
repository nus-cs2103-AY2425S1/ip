public class Todo extends Task {

    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        String task = super.toString();
        return "[T] " + task;
    }
}
