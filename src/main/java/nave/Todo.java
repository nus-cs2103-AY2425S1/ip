package nave;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code Todo} class represents a simple to-do task with a name.
 * <p>
 * It extends the {@code Task} class to provide functionality specific to
 * to-do tasks. This includes handling input for task creation, formatting
 * the task for display, and converting it to a file-compatible format.
 * </p>
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with the specified name.
     *
     * @param name the name of the to-do task
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Creates a new {@code Todo} instance based on the provided input string.
     * <p>
     * The method uses a regular expression to validate the input and ensure
     * it contains a valid task name. If the input does not match the expected
     * pattern, a {@code WrongInputException} is thrown.
     * </p>
     *
     * @param input the input string containing the task name
     * @return a {@code Todo} object if the input is valid
     * @throws WrongInputException if the input does not contain a valid task name
     */
    public static Todo handleInput(String input) throws WrongInputException {
        Pattern correctPattern = Pattern.compile("((\\w+\\s*)+)");
        Matcher correctMatcher = correctPattern.matcher(input);

        if (correctMatcher.matches()) {
            return new Todo(input);
        } else {
            throw new WrongInputException("This todo doesn't have a name!");
        }
    }

    /**
     * Returns a response message indicating that a new to-do task has been added.
     * <p>
     * This method is used to generate a confirmation message when a new to-do
     * task is created.
     * </p>
     *
     * @return a string message indicating the addition of a new to-do task
     */
    public String creationResponse() {
        return "Ok! I've added a new todo task:\n" + this
                + "\n";
    }

    /**
     * Returns the string format of the to-do task suitable for saving to a file.
     * <p>
     * This method appends a newline character to the string format returned by
     * the {@code toFileFormat} method in the {@code Task} class.
     * </p>
     *
     * @return the file format string of the to-do task with a newline
     */
    @Override
    public String toFileFormat() {
        return super.toFileFormat() + System.lineSeparator();
    }

    /**
     * Returns a string representation of the to-do task.
     * <p>
     * This method includes a prefix indicating that the task is a to-do item,
     * followed by the status and name of the task.
     * </p>
     *
     * @return a string representation of the to-do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
