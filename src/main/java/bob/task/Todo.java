package bob.task;

public class Todo extends Task {

    /**
     * Constructor to initialise a task.
     *
     * @param description Input based on user.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor to initialise a task that has been recorded before.
     *
     * @param description Input based on user.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }


    // Returns the letter representing todo.
    @Override
    public String getTaskLetter() {
        return "T";
    }

    /**
     * Returns a string representation of the file format in which we store the Todo.
     */
    @Override
    public String getFileFormat() {
        String part1 = super.getFileFormat();
        return part1;
    }
}
