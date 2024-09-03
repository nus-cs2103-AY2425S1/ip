package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Event;
import yapbot.tasks.Task;
import yapbot.util.Storage;
import yapbot.util.TaskList;
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
        boolean containsFrom = taskDetails.contains("/from");
        boolean containsTo = taskDetails.contains("/to");

        if (!containsFrom && !containsTo) {
            throw new YapBotException("Error, start and end times not detected.\nSupply start time using "
                    + "\"/from\" (eg. /from Monday 1pm).\nSupply end time using \"/to\" (eg. /to Tuesday 1pm).");
        }

        if (!containsFrom) {
            throw new YapBotException("Error, start time not detected.\nSupply start time using \"/from\" (eg. "
                    + "/from Monday 1pm).");
        }

        if (!containsTo) {
            throw new YapBotException("Error, end time not detected.\nSupply end time using \"/to\" (eg. /to "
                    + "Tuesday 1pm).");
        }

        String taskName = taskDetails.substring(0, taskDetails.indexOf("/")).strip();
        String taskDeadlines = taskDetails.substring(taskDetails.indexOf("/"));
        int fromIndex = taskDeadlines.indexOf("/from");
        int toIndex = taskDeadlines.indexOf("/to");
        String fromStr;
        String toStr;

        // Checks order of /from and /to and changes to upper case to align with date time formatter
        if (toIndex > fromIndex) {
            fromStr = taskDeadlines.substring(fromIndex + 5, toIndex).strip().toUpperCase();
            toStr = taskDeadlines.substring(toIndex + 3).strip().toUpperCase();
        } else {
            toStr = taskDeadlines.substring(toIndex + 3, fromIndex).strip().toUpperCase();
            fromStr = taskDeadlines.substring(fromIndex + 5).strip().toUpperCase();
        }

        Task task = new Event(taskName, fromStr, toStr);
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
