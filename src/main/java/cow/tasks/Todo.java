package cow.tasks;

import cow.tasks.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Alternate constructor for loading isDone directly
     * @param isDone 1 or 0 indicating if task is done
     * @param description The Description of the tasks.Todo
     */
    public Todo(String isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * A string that matches the format for writing it to file
     * @return A string to be written to a txt file
     */
    @Override
    public String getSaveData() {
        return "T | " + super.getSaveData();
    }
}
