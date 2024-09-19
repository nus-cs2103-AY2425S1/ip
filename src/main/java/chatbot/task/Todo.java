package chatbot.task;

/**
 * Represents the concept of a Todo task that the user adds into his todolist
 * The Todo class extends the abstract class of Task
 */
public class Todo extends Task {
    /**
     * Constructs the Todo object, taking in 1 argument
     *
     * @param name Name of Todo
     */
    public Todo(String name) {
        this(name, false);
    }

    /**
     * Constructs the Todo object, taking in 2 arguments
     *
     * @param name Name of Todo
     * @param isDone Boolean value representing whether the task has been marked as completed
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Displays the type of the task along with other details
     *
     * @return String representation of the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Encodes the Todo task into a string, to be written to a text file
     *
     * @return String encoding of the Todo task
     */
    @Override
    public String encode() {
        return "T|" + super.encode();
    }
}
