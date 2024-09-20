package jbot.task;

/**
 * Represents a task with a simple description.
 * A <code>ToDoTask</code> consists of a name and a status indicating whether the task is done or not.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a <code>ToDoTask</code> with the specified input string.
     * The input string must include the task description in the format 'todo [description]'.
     *
     * @param input The input string containing the task description.
     * @throws EmptyToDoDescriptionException If the description is empty.
     */
    public ToDoTask(String input) throws EmptyToDoDescriptionException {
        String name = input.substring(4).trim();

        if (name.isEmpty()) {
            throw new EmptyToDoDescriptionException("The description of a todo task cannot be empty.");
        }

        this.setName(name);
        this.setTaskTypeSymbol("T");
    }

}
