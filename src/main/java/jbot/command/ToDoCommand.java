package jbot.command;

import jbot.task.EmptyToDoDescriptionException;
import jbot.task.Task;
import jbot.task.ToDoTask;
import jbot.util.TaskList;

/**
 * Represents a command that adds a ToDo task to the task list.
 * This command creates a new {@link ToDoTask} from the provided input, adds it to the task list,
 * and prints a confirmation message with the added task.
 * Extends {@link AddCommand} to provide common functionality for adding tasks.
 */
public class ToDoCommand extends AddCommand {

    private static final ToDoCommand instance = new ToDoCommand();

    private ToDoCommand() {
    }

    /**
     * Returns the singleton instance of {@link ToDoCommand}.
     *
     * @return The singleton instance of {@link ToDoCommand}.
     */
    public static ToDoCommand getInstance() {
        return instance;
    }

    /**
     * Executes the ToDo command using the provided input string.
     * Creates a new {@link ToDoTask} from the input, adds it to the task list,
     * and returns a confirmation message with the added task. If the description is empty,
     * returns an error message instead.
     *
     * @param input The user input string containing the command and its arguments.
     * @return A string containing the confirmation message and the added task,
     * or an error message if the task description is empty.
     */
    @Override
    public String run(String input) {
        try {
            Task task = new ToDoTask(input);
            TaskList.add(task);

            StringBuilder result = new StringBuilder();
            result.append("Got it. I've added this task:\n");
            result.append(String.format("  %1$s\n", task));

            String superResult = super.run(input);
            result.append(superResult);

            return result.toString();
        } catch (EmptyToDoDescriptionException e) {
            return e.getMessage();
        }
    }
}