package tasks;

import exceptions.InputException;

/**
 * Represents a tasks.ToDo task.
 * A tasks.ToDo task is a simple task with a description but without any associated date or time.
 */
public class ToDo extends Task{

    /**
     * Constructs a new tasks.ToDo task with the given description.
     *
     * @param description the description of the tasks.ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a tasks.ToDo task from the given input string.
     * The input string must start with the command "todo" followed by the task description.
     *
     * @param input the input string containing the task details.
     * @return the created tasks.ToDo task.
     * @throws InputException if the input format is incorrect or if the description is missing.
     */
    @Override
    public Task createTask(String input) throws InputException {
        if (input.equalsIgnoreCase("todo")) {
            throw new InputException("To add a tasks.ToDo task, use the following format: todo <task description>");
        }
        String description = input.trim();
        if (description.isEmpty()) {
            throw new InputException("You need to describe your Todo!");
        }
        return new ToDo(description);
    }

    /**
     * Returns a string representation of the tasks.ToDo task.
     * The string includes a "[T]" prefix to indicate that it is a tasks.ToDo task, followed by the task status and description.
     *
     * @return a string representing the tasks.ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of a tasks.ToDo when saving to file.
     *
     * @return a string representation of the tasks.ToDo.
     */
    @Override
    public String encode() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
