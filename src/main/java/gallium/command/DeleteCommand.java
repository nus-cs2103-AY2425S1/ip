package gallium.command;

import gallium.main.GalliumException;
import gallium.main.Storage;
import gallium.main.TaskList;
import gallium.main.Ui;

import gallium.task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private String message;

    /**
     * Constructs a DeleteCommand with the specified message.
     * 
     * @param message The message containing the index of the task to delete.
     */
    public DeleteCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the delete command to delete a task from the task list.
     * 
     * @param tasklist The list of tasks to execute the command on.
     * @param ui       The user interface that will interact with user.
     * @param storage  The storage that will save any changes made by the command.
     * @throws GalliumException If an error occurs during the execution of the
     *                          command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GalliumException {
        try {
            String indexString = message.split("delete ")[1];
            int index = Integer.parseInt(indexString);
            Task task = taskList.getTask(index - 1);
            Task.count--;
            ui.printDelete(task);
            taskList.remove(index - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GalliumException("Please put a space after your command! \nExample: delete 10");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.showWrongIndex();
        }
    }
}
