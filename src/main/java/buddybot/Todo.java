package buddybot;

/**
 * Class for To Do
 */
public class Todo extends Task {

    /**
     * Constructor for To Do
     * @param description
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Return a String of the To Do the file reader recognises
     * @return String
     */
    public String toFile() {
        return "T" + super.toFile();
    }

    /**
     * Return a String of the Task with its status and description
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
