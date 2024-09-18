package command;

import java.util.List;

import exceptions.DelphiException;
import exceptions.InvalidInputException;
import exceptions.TaskNotFoundException;
import parser.Parser;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

/**
 * Command to update a task in the task list after searching for it based on keyword.
 */
public class UpdateTaskCommand extends Command {

    /**
     * Constructs an UpdateTaskCommand with the given input string.
     *
     * @param s The input string that contains the description of the task to be updated.
     */
    public UpdateTaskCommand(String s) {
        super(s);
    }

    /**
     * Executes the task edit based on the input provided. The input is checked to contain "/by" or "/from"
     * to determine the type of task (deadline or event) and updates it accordingly.
     *
     * @param t The task list to search through.
     * @param s The storage instance used to save the updated task list to disk.
     * @param ui The user interface instance used to interact with the user.
     * @return A message indicating the success or failure of the operation.
     * @throws DelphiException If an invalid input is given or if no task is found to update.
     */
    public String execute(TaskList t, Storage s, Ui ui, Parser p) throws DelphiException {
        String str;
        if (getInput().contains("/by")) {
            str = getInput().substring(7, getInput().indexOf("/by"));
        } else if (getInput().contains("/from")) {
            str = getInput().substring(7, getInput().indexOf("/from"));
        } else {
            throw new InvalidInputException();
        }
        List<Task> searchResults = t.findTask(str);
        if (searchResults.size() == 0) {
            throw new TaskNotFoundException();
        } else if (searchResults.size() > 1) {
            return "please be more specific";
        } else {
            searchResults.get(0).editTask(getInput().substring(7), p);
            s.writeToHardDisk(t.getTasks());
            return "task has been updated!";
        }
    }
}
