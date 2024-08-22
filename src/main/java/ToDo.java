public class ToDo extends Task {

    /**
     * Constructor for ToDo task
     * @param description description of task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns string representation of ToDo
     * @return "[T]" in front of string representation of Task
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
