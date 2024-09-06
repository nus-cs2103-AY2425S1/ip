package elara.command;

import elara.storage.Storage;
import elara.task.InvalidInputException;
import elara.task.TaskList;
import elara.ui.Ui;

public class MarkCommand implements Command {
    private final String fullInput;

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
