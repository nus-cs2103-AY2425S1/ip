/**
 * Represents a ToDo task.
 * A ToDo task is a simple task with a description but without any associated date or time.
 */
public class ToDo extends Task{

    /**
     * Constructs a new ToDo task with the given description.
     *
     * @param description the description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a ToDo task from the given input string.
     * The input string must start with the command "todo" followed by the task description.
     *
     * @param input the input string containing the task details.
     * @return the created ToDo task.
     * @throws InputException if the input format is incorrect or if the description is missing.
     */
    @Override
    public Task createTask(String input) throws InputException{
        if (input.equalsIgnoreCase("todo")) {
            throw new InputException("To add a ToDo task, use the following format: todo <task description>");
        }
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new InputException("You need to describe your Todo!");
        }
        return new ToDo(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     * The string includes a "[T]" prefix to indicate that it is a ToDo task, followed by the task status and description.
     *
     * @return a string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
