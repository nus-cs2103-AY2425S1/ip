package alexer.task;

public class Todo extends Task {
    /**
     * Creates a new to-do task
     *
     * @param description The description of the task, inherited from Task class
     */
    public Todo(String description) {
        super(description);
        type = TaskType.TODO;
    }

    /**
     * Creates a to-do task from a task string, used
     * when loading tasks from the save file.
     *
     * @param taskString the raw task string saved to file
     * @return a new to-do task if the task string is valid, null otherwise
     */
    public static Todo fromTaskString(String taskString) {
        String[] parts = taskString.split("\\|");
        if (parts.length < 3)
            return null;

        Todo todo = new Todo(parts[2]);
        if (parts[1].equals("1")) {
            todo.markAsDone();
        }

        return todo;
    }

    /** Returns the task type prefix **/
    protected String getTaskType() {
        return "T";
    }

    /**
     * Converts the task into the task string form for serialisation
     * and saving to file. This also ensures a balance between human readability
     * and ease of machine parsing
     *
     * @return the task string of the current task
     */
    @Override
    public String toTaskString() {
        return String.format("%s|%d|%s",
                getTaskType(), isDone ? 1 : 0, description);
    }

    /**
     * Converts the task into string form for printing, and
     * primarily focuses on human readability.
     *
     * @return the human-readable string form of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getTaskType(), super.toString());
    }
}
