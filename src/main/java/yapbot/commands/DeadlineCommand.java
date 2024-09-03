package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Deadline;
import yapbot.tasks.Task;
import yapbot.util.Storage;
import yapbot.util.TaskList;
import yapbot.util.Ui;

public class DeadlineCommand extends Command {
    private String taskDetails;

    public DeadlineCommand(String taskDetails) throws YapBotException {
        if (taskDetails.isEmpty()) {
            throw new YapBotException("Error, Automated Task Suggestion module offline."
                    + "\nTask details must be manually entered.");
        }

        this.taskDetails = taskDetails;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws YapBotException {
        if (!taskDetails.contains("/by")) {
            throw new YapBotException("Error, Deadline Prediction module offline.\nSupply a deadline using "
                    + "\"/by\" (eg. /by Monday 1pm).");
        }

        String taskName = taskDetails.substring(0, taskDetails.indexOf("/by")).strip();
        String deadlineStr = taskDetails.substring(taskDetails.indexOf("/by") + 3).strip().toUpperCase();

        Task task = new Deadline(taskName, deadlineStr);
        tasks.addTask(task);

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
