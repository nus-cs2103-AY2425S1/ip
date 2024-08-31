package task;
import exception.InputFormatException;

/**
 * The Todo class represents a simple task with only a description.
 * It extends the Task class.
 */
public class Todo extends Task{

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task in a format suitable for file storage.
     * The format is: "T | doneStatus | description", where doneStatus is 1 if the task is done, 0 otherwise.
     *
     * @return A string representation of the todo task for file storage.
     */
    public String toFileFormatString() {
        return String.format("T | %s", super.toFileFormatString());
    }

    /**
     * Returns a string representation of the todo task, including its status and description.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return String.format("[T] %s\n", super.toString());
    }


    public static boolean matchTodo(String s) {
        return s.startsWith("todo");
    }
}
