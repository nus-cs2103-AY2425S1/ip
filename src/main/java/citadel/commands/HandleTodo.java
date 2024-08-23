package citadel.commands;

import citadel.Task.Task;
import citadel.Task.ToDo;
import citadel.Task.TaskList;
import citadel.ui.TextUI;
import citadel.exception.CitadelTaskNoInput;

/**
 * Represents the command to handle the creation of a to-do task in the Citadel application.
 * This class extends {@link Command} and provides functionality to add a to-do task to the task list.
 */
public class HandleTodo extends Command {

    /**
     * Constructs a new {@code HandleTodo} command with the specified input and task list.
     *
     * @param input The user input associated with the to-do command.
     * @param tasks The task list to which the to-do task will be added.
     */
    public HandleTodo(String input, TaskList tasks) {
        super(input, tasks);
    }

    /**
     * Executes the handle to-do command.
     * <p>
     * This method parses the user input to extract the to-do task description,
     * creates a new {@link ToDo} task, adds it to the task list, and displays a confirmation message.
     * If the input is empty, a {@link CitadelTaskNoInput} exception is thrown.
     * </p>
     *
     * @throws CitadelTaskNoInput If the input is empty or invalid.
     */
    @Override
    public void run() throws CitadelTaskNoInput {
        Task t;
        String todo = input.substring(5).trim();
        if (todo.isEmpty()) {
            throw new CitadelTaskNoInput();
        }
        t = new ToDo(todo);
        tasks.add(t);
        TextUI.printTask(t, tasks);
    }
}
