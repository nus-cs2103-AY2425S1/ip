package froggy;

/**
 * Represents a task with only a description.
 */
public class Todo extends Task {

    /**
     * Constructor that takes in a description for task.
     *
     * @param description String input.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toTxt() {
        return "T " + super.toTxt();
    }

    @Override
    public boolean equals(Task task) {
        if (this == task) {
            return true;
        }
        if (!(task instanceof Todo)) {
            return false;
        }
        Todo todo = (Todo) task;
        return description.equals(todo.getDescription());
    }
}
