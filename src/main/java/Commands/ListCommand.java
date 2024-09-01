package Commands;

import Exceptions.BrockException;
import Storage.Storage;
import Tasks.TaskList;
import Ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws BrockException {
        String tasksString = storage.readFromFile();
        int totalTasks = tasks.numTasks();

        String responseBody;
        if (totalTasks == 0) {
            responseBody = "No current tasks!";
        } else {
            responseBody = tasksString;
        }

        ui.displayResponse((totalTasks == 1
                ? "Here is the task in your list:\n"
                : "Here are the tasks in your list:\n")
                + responseBody);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
