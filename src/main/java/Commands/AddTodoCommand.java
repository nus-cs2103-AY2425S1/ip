package Commands;

import Exceptions.DelphiException;
import Storage.Storage;
import TaskList.TaskList;
import Tasks.Todo;
import UI.Ui;

/**
 * Command to add a new Todo task to the task list.
 * This command creates a new Todo task based on the input and adds it to the task list.
 */
public class AddTodoCommand extends Command {

    /**
     * Constructs an AddTodoCommand with the given input string.
     *
     * @param s The input string that contains the description of the Todo task to be added.
     */
    public AddTodoCommand(String s) {
        super(s);
    }

    /**
     * Executes the command to add a new Todo task to the task list.
     * This method creates a new Todo task from the input string, adds it to the task list,
     * writes the updated task list to storage, and updates the user interface to reflect the addition.
     *
     * @param t  The task list where the new Todo task will be added.
     * @param s  The storage object to save the updated task list.
     * @param ui The user interface to reflect the addition of the new Todo task.
     * @throws DelphiException if there is an error processing the input or adding the task.
     */
    @Override
    public String execute(TaskList t, Storage s, Ui ui) throws DelphiException {
        // Create a new Todo task from the input string, starting from the 5th character
        Todo newTodo = new Todo(getInput().trim().substring(4));

        // Add the new Todo task to the task list
        t.addTask(newTodo); // Note: This will be removed once addTask is simplified to just add to the list

        //assert that the task was added
        assert t.getSize() > 0 : "TaskList should not be empty";

        // Write the updated task list to storage
        s.writeToHardDisk(t.getTasks());

        // Update the user interface to reflect the addition of the new Todo task
        return ui.taskMessage(newTodo, t.getTasks().size());
    }
}

