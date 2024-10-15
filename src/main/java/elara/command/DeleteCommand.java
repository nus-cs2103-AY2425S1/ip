package elara.command;

import elara.task.InvalidInputException;
import elara.utils.Storage;
import elara.utils.TaskList;
import elara.utils.Ui;

/**
 * Represents a command that deletes a task from the task list that can be executed in the Elara chatbot.
 */
public class DeleteCommand implements Command {
    private final String fullInput;

    /**
     * Constructs a new instance of DeleteCommand.
     *
     * @param fullInput The input provided by the user, which includes the task index to delete.
     */
    public DeleteCommand(String fullInput) {
        this.fullInput = fullInput;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        if (fullInput.trim().equals("delete")) {
            throw new InvalidInputException("Append task index after command!");
        }

        try {
            int i = Integer.parseInt(fullInput.split(" ", 2)[1]) - 1;

            if (i < 0 || i >= taskList.getTasks().size()) {
                throw new InvalidInputException("Task index out of bounds!");
            }

            ui.showRemoveTaskMessage(taskList.getTask(i));
            taskList.deleteTask(i);
            ui.showNumOfTasksMessage(taskList);
            storage.write(taskList);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Task index must be a number!");
        }
    }
}
