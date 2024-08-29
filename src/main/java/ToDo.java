public class ToDo extends Task {

    /**
     * Constructor for ToDo task
     *
     * @param description description of task
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Constructor for ToDo task with status
     *
     * @param description description of task
     * @param isComplete status of task
     */
    public ToDo(String description, boolean isComplete) {
        super(description, TaskType.TODO, isComplete);
    }

    /**
     * Returns string representation of ToDO for file writing
     *
     * @return String formatted by Task class
     */
    @Override
    public String getSaveFormat() {
        return super.getSaveFormat();
    }

    /**
     * Returns string representation of ToDo
     *
     * @return "[T]" in front of string representation of Task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
