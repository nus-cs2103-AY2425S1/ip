package juno.task;
public class Todo extends Task {

    public Todo(String description, String taskType) {
        super(description, taskType);
    }

    @Override
    public String toString() {
        return "[📝 ToDo] " + super.toString() + " - Let's get this done! 💪";
    }
}
