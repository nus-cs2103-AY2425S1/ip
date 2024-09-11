package Commands;

import Exceptions.DelphiException;
import Parser.DateParser;
import Storage.Storage;
import TaskList.TaskList;
import Tasks.Event;
import UI.Ui;

/**
 * Command to add a new Event task to the task list.
 * This command creates a new Event task based on the input and adds it to the task list.
 */
public class AddEventCommand extends Command {

    /**
     * Constructs an AddEventCommand with the given input string.
     *
     * @param s The input string that contains the description of the Event task to be added.
     */
    public AddEventCommand(String s) {
        super(s);
    }

    /**
     * Executes the command to add a new Event task to the task list.
     * This method creates a new Event task from the input string, adds it to the task list,
     * writes the updated task list to storage, and updates the user interface to reflect the addition.
     *
     * @param t  The task list where the new Event task will be added.
     * @param s  The storage object to save the updated task list.
     * @param ui The user interface to reflect the addition of the new Event task.
     * @throws DelphiException if there is an error processing the input or adding the task.
     */
    @Override
    public String execute(TaskList t, Storage s, Ui ui) throws DelphiException {
        // Create a new Event task from the input string, starting from the 9th character
        Event newEvent = new Event(getInput().trim().substring(5), new DateParser());

        // Add the new Event task to the task list
        t.addTask(newEvent);

        //assert that the task was added
        assert t.getSize() > 0 : "TaskList should not be empty";

        // Write the updated task list to storage
        s.writeToHardDisk(t.getTasks());

        // Update the user interface to reflect the addition of the new Event task
        return ui.taskMessage(newEvent, t.getTasks().size());
    }
}

