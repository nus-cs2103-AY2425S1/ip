package yapbot.commands;

import yapbot.exceptions.YapBotException;
import yapbot.tasks.Deadline;
import yapbot.tasks.Task;
import yapbot.util.Storage;
import yapbot.util.TaskList;

/**
 * Command for creation of Deadline tasks.
 */
public class DeadlineCommand extends Command {
    private String taskDetails;

    /**
     * Creates a DeadlineCommand instance.
     *
     * @param taskDetails Details of the Deadline task to be created.
     * @throws YapBotException If task details are empty.
     */
    public DeadlineCommand(String taskDetails) throws YapBotException {
        if (taskDetails.isEmpty()) {
            throw new YapBotException("Error, Automated Task Suggestion module offline."
                    + "\nTask details must be manually entered.");
        }

        this.taskDetails = taskDetails;
    }

    /**
     * {@inheritDoc}
     * Creates and adds the Deadline Task to TaskList.
     *
     * @throws YapBotException If task details do not contain a deadline.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws YapBotException {
        if (!taskDetails.contains("/by")) {
            throw new YapBotException("Error, Deadline Prediction module offline.\nSupply a deadline using "
                    + "\"/by\" (eg. /by Monday 1pm).");
        }

        String taskName = taskDetails.substring(0, taskDetails.indexOf("/by")).strip();

        // Changes to upper case to align with date time formatter
        String deadlineStr = taskDetails.substring(taskDetails.indexOf("/by") + 3).strip().toUpperCase();

        Task task = new Deadline(taskName, deadlineStr);
        tasks.addTask(task);

        String successMessage = "Adding Task...\nSuccess\nTask added to database:\n" + "  "
                + task + "\n" + "Total tasks: " + tasks.size();

        return successMessage;
    }

}
