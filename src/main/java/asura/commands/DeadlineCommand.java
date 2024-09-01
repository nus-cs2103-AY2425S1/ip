package asura.commands;

import asura.data.exception.AsuraException;
import asura.data.tasks.Deadline;
import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents a user inputting a DeadlineCommand.
 */
public class DeadlineCommand extends Command {

    List<String> descriptionArray;
    List<String> dateArray;

    /**
     * Creates a DeadlineCommand with the specified description and date.
     * @param descriptionArray An array of the description specified by the user.
     * @param dateArray An array of the date specified by the user.
     */
    public DeadlineCommand(List<String> descriptionArray, List<String> dateArray) {
        this.descriptionArray = descriptionArray;
        this.dateArray = dateArray;
    }

    /**
     * Adds a Deadline task into the task list and outputs feedback to the user.
     * @param tasklist The list of tasks of the user.
     * @param ui The UI object to give user feedback.
     * @param storage The storage object to save/load tasks.
     * @throws AsuraException If saving user tasks fails.
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws AsuraException {
        String dateStr = String.join(" ", dateArray);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
        Deadline newDeadline = new Deadline(String.join(" ", descriptionArray), dateTime);
        tasklist.addTask(newDeadline);
        storage.save(tasklist.getTaskList());
        output.append("Got it. I've added this deadline:\n").append(newDeadline).append("\n").append("Now you have ")
                .append(tasklist.size()).append(" tasks in your list.\n");
        ui.printString(output.toString());
    }

    /**
     * Indicates that the user does not want to terminate the program.
     * @return A boolean representing whether the program is to be terminated, in this case false.
     */
    public boolean isExit() {
        return false;
    }
}
