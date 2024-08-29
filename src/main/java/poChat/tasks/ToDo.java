package poChat.tasks;

public class ToDo extends Task {
    public ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
    }

    public ToDo(String taskDescription) {
        this(taskDescription, false);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
