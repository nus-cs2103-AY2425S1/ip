package elara.command;

import elara.task.InvalidInputException;
import elara.utils.Storage;
import elara.utils.TaskList;
import elara.utils.Ui;

/**
 * Represents a command that finds tasks containing a specified keyword in the task description.
 */
public class FindCommand implements Command {
    private final String fullInput;

    /**
     * Constructs a new FindCommand.
     *
     * @param fullInput The full user input, including keyword to be searched.
     */
    public FindCommand(String fullInput) {
        this.fullInput = fullInput;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        if (fullInput.equals("find")) {
            throw new InvalidInputException("Append keyword after command.");
        }

        String keyword = fullInput.toLowerCase().split(" ", 2)[1].trim();
        ui.showFindTasksMessage(taskList.findTasks(keyword));
    }
}
