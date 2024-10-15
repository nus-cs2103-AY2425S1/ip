package elara.command;

import elara.task.InvalidInputException;
import elara.utils.Storage;
import elara.utils.TaskList;
import elara.utils.Ui;

/**
 * Represents a command that marks a task as done that can be executed in the Elara chatbot.
 */
public class MarkCommand implements Command {
    private final String fullInput;

    /**
     * Creates an instance of the MarkCommand class.
     *
     * @param fullInput The input provided by the user, which includes the task index to delete.
     */
    public MarkCommand(String fullInput) {
        this.fullInput = fullInput;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        if (fullInput.trim().equals("mark")) {
            throw new InvalidInputException("Append task index after command!");
        }

        try {
            int i = Integer.parseInt(fullInput.split(" ", 2)[1]) - 1;

            if (i < 0 || i >= taskList.getTasks().size()) {
                throw new InvalidInputException("Task index out of bounds!");
            }

            taskList.markTask(i);
            ui.showMarkedTaskMessage(taskList.getTask(i));
            storage.write(taskList);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Task index must be a number!");
        }
    }
}
