package asura.commands;

import asura.data.exception.AsuraException;
import asura.data.tasks.Event;
import asura.data.tasks.TaskList;
import asura.storage.Storage;
import asura.ui.Ui;

import java.util.List;

/**
 * Represents a user inputting a EventCommand.
 */
public class EventCommand extends Command {

    List<String> descriptionArray;
    List<String> fromArray;
    List<String> toArray;

    /**
     * Creates a EventCommand with the specified description, from date and to date.
     * @param descriptionArray An array of the description specified by the user.
     * @param fromArray An array of the from date specified by the user.
     * @param toArray An array of the to date specified by the user.
     */
    public EventCommand(List<String> descriptionArray, List<String> fromArray, List<String> toArray) {
        this.descriptionArray = descriptionArray;
        this.fromArray = fromArray;
        this.toArray = toArray;
    }

    /**
     * Adds a Event task into the task list and outputs feedback to the user.
     * @param tasklist The list of tasks of the user.
     * @param ui The UI object to give user feedback.
     * @param storage The storage object to save/load tasks.
     * @throws AsuraException If saving user tasks fails.
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws AsuraException {
        Event newEvent = new Event(String.join(" ", descriptionArray), String.join(" ", fromArray), String.join(" ", toArray));
        tasklist.addTask(newEvent);
        storage.save(tasklist.getTaskList());
        output.append("Got it. I've added this event:\n").append(newEvent.toString()).append("\n").append("Now you have ")
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
