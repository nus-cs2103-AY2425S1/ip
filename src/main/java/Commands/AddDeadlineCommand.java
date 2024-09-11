package Commands;

import Exceptions.DelphiException;
import Parser.DateParser;
import Storage.Storage;
import TaskList.TaskList;
import Tasks.Deadline;
import UI.Ui;

/**
 * Command to add a new Deadline task to the task list.
 * This command creates a new Deadline task based on the input and adds it to the task list.
 */
public class AddDeadlineCommand extends Command {

    /**
     * Constructs an AddDeadlineCommand with the given input string.
     *
     * @param s The input string that contains the description of the Deadline task to be added.
     */
    public AddDeadlineCommand(String s) {
        super(s);
    }

    /**
     * Executes the command to add a new Deadline task to the task list.
     * This method creates a new Deadline task from the input string, adds it to the task list,
     * writes the updated task list to storage, and updates the user interface to reflect the addition.
     *
     * @param t  The task list where the new Deadline task will be added.
     * @param s  The storage object to save the updated task list.
     * @param ui The user interface to reflect the addition of the new Deadline task.
     * @throws DelphiException if there is an error processing the input or adding the task.
     */
    @Override
    public String execute(TaskList t, Storage s, Ui ui) throws DelphiException {
        // Create a new DateParser instance to parse the date
        DateParser d = new DateParser();

        // Create a new Deadline task from the input string, starting from the 9th character
        Deadline newDeadline = new Deadline(getInput().trim().substring(9), d);

        // Add the new Deadline task to the task list
        t.addTask(newDeadline);

        //assert that the task was added
        assert t.getSize() > 0 : "TaskList should not be empty";

        // Write the updated task list to storage
        s.writeToHardDisk(t.getTasks());

        // Update the user interface to reflect the addition of the new Deadline task
        return ui.taskMessage(newDeadline, t.getTasks().size());
    }
}
