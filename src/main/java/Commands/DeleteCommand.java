package Commands;

import Exceptions.BrockException;
import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;
import Utility.Utility;

public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        super(command);
    }

    private void validateDelete(TaskList tasks) throws BrockException {
        String command = super.getCommand();
        String[] commandWords = command.split(" ");
        int commandLength = commandWords.length;

        if (commandLength == 1) {
            throw new BrockException("Missing task number!");
        }
        if (commandLength > 2 || Utility.isNotInteger(commandWords[1])) {
            throw new BrockException("Delete command is in the form delete <task-number>!");
        }

        int taskNumber = Integer.parseInt(commandWords[1]);
        int totalTasks = tasks.numTasks();
        if (taskNumber > totalTasks || taskNumber < 1) {
            throw new BrockException("Task number does not exist!");
        }
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws BrockException {
        validateDelete(tasks);

        String command = super.getCommand();
        int targetIndex = Utility.getTargetIndex(command);
        tasks.removeFromList(targetIndex);

        ui.displayResponse("Noted. I've removed this task:\n"
                + "  "
                + tasks.getTaskDetails(targetIndex)
                + '\n'
                + tasks.getTasksSummary());

        // Update the save file
        String fileContents = storage.readFromFile(targetIndex + 1);
        storage.writeToFile("", false);
        storage.writeToFile(fileContents, true);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
