package rotodo.commands;

import java.io.IOException;

import rotodo.exception.InvalidInputException;
import rotodo.processes.Gui;
import rotodo.processes.Storage;
import rotodo.tasklist.TaskList;

/**
 * The DeleteCommand class encapsulates the specific
 * type of Command that executes a delete action.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Initialises DeleteCommand to be executed. Accepts
     * a task index to delete.
     *
     * @param index of task to delete, 0 to n - 1
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) {
        assert gui != null;
        assert tasks != null;
        try {
            gui.addMessage(tasks.deleteTask(index));
        } catch (InvalidInputException e) {
            gui.addMessage(e.toString());
        }
        try {
            storage.saveList(tasks);
        } catch (IOException e) {
            gui.addMessage("\nUnable to save list :(\nRoTodo is sorry");
        }
    }
}
