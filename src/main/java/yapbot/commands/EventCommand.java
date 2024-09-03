package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Task;
import yapbot.util.Storage;
import yapbot.util.TaskList;
import yapbot.util.Tasktype;
import yapbot.util.Ui;

public class EventCommand extends Command {

    private String taskDetails;

    public EventCommand(String taskDetails) throws YapBotException {
        if (taskDetails.isEmpty()) {
            throw new YapBotException("Error, Automated Task Suggestion module offline."
                    + "\nTask details must be manually entered.");
        }

        this.taskDetails = taskDetails;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws YapBotException {
        Task task = tasks.addTask(Tasktype.EVENT,this.taskDetails);
        String successMessage = "Adding Task...\nSuccess\nTask added to database:\n" + "  "
                + task + "\n" + "Total tasks: " + tasks.size();
        ui.printOutput(successMessage);

        return true;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
