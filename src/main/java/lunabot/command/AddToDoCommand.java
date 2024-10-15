package lunabot.command;

import lunabot.exception.LunaBotException;
import lunabot.storage.Storage;
import lunabot.task.TaskList;
import lunabot.task.ToDo;
import lunabot.ui.Ui;

/**
 * Command to add ToDo task to the taskList.
 */
public class AddToDoCommand extends Command {
    private String description;

    /**
     * Constructs an AddToDoCommand from user input.
     *
     * @param input Full user input is taken in for the command.
     * @throws LunaBotException Handles wrong input format or empty description.
     *
     */
    public AddToDoCommand(String input) throws LunaBotException {
        this.description = input.substring(2);

        if (description.isEmpty()) {
            throw new LunaBotException("Description of task cannot be empty. Please fill in the required fields.");
        }
    }

    /**
     * @param taskList Current list of tasks for the ToDo task to be added to.
     * @param ui User interface that handles user input interactions and display messages.
     * @param storage Storage system to save or load tasks to/from a file.
     * @throws LunaBotException If an error occurs while executing the command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LunaBotException {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";

        ToDo todo = new ToDo(description);
        taskList.addTask(todo);
        storage.save(taskList.getTasks());
        return ui.printTaskAdded(todo, taskList.size());
    }
}
