package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Event;
import yapbot.tasks.Task;
import yapbot.util.Storage;
import yapbot.util.TaskList;

/**
 * Command for creation of Event tasks.
 */
public class EventCommand extends Command {

    private String taskDetails;

    /**
     * Creates an EventCommand instance.
     *
     * @param taskDetails Details of the Event task to be created.
     * @throws YapBotException If task details are empty.
     */
    public EventCommand(String taskDetails) throws YapBotException {
        if (taskDetails.isEmpty()) {
            throw new YapBotException("Error, Automated Task Suggestion module offline."
                    + "\nTask details must be manually entered.");
        }

        this.taskDetails = taskDetails;
    }

    /**
     * {@inheritDoc}
     * Creates and adds the Event Task to TaskList.
     *
     * @throws YapBotException If task details do not contain start and end times/dates.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws YapBotException {
        boolean containsFrom = taskDetails.contains("/from");
        boolean containsTo = taskDetails.contains("/to");

        if (!containsFrom && !containsTo) {
            throw new YapBotException("Error, start and end times not detected.\nSupply start time using "
                    + "\"/from\" (eg. /from Monday 1pm).\nSupply end time using \"/to\" (eg. /to Tuesday 1pm).");
        }

        if (!containsFrom) {
            throw new YapBotException("Error, start time not deteced.\nSupply start time using \"/from\" (eg. "
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

        return successMessage;
    }

}
