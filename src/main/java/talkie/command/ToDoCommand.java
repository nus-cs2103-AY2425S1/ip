package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieMissingArgumentException;
import talkie.task.Task;
import talkie.task.TaskList;
import talkie.task.ToDo;

/**
 * Represents a command to create a new ToDo task in the Talkie application.
 * The command processes user input to add a new ToDo task with the specified description.
 */
public class ToDoCommand extends Command {

    private String fullCommand;

    /**
     * Constructs a new {@code ToDoCommand} with the full user input.
     *
     * @param fullCommand The full user input containing the command type and task description.
     */
    public ToDoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the {@code ToDoCommand} by creating a new ToDo task with the given description.
     *
     * @param tasks   The task list containing all current tasks.
     * @param ui      The UI component used to display messages to the user.
     * @param storage The storage component used to save task data.
     * @throws TalkieMissingArgumentException If the description of the ToDo task is missing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TalkieMissingArgumentException {
        String[] parts = fullCommand.split(" ", 2); // Split into type and the rest of the input

        if (parts.length == 2) {
            String details = parts[1]; // rest of the input (e.g., task description)
            Task newToDo = new ToDo(details.trim());
            tasks.addTask(newToDo);
            ui.addMessage(newToDo, tasks.size());
        } else {
            throw new TalkieMissingArgumentException(parts[0], "The 'description' of todo cannot be empty.");
        }
    }

    /**
     * Indicates that this command does not terminate the application.
     *
     * @return {@code false}, as this command does not end the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
