package pixel.command;

import pixel.PixelException;
import pixel.Storage;
import pixel.Ui;
import pixel.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the list command, displaying all tasks in the task list.
     *
     * @param taskList The task list containing the tasks.
     * @param ui       The user interface to interact with the user.
     * @param storage  The storage to save and load tasks.
     * @throws PixelException If there is an error executing the command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        String[] outputs = new String[taskList.size() + 1];
        outputs[0] = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            outputs[i + 1] = index + ". " + taskList.getTaskAtIndex(i);
        }
        ui.pixelSays(outputs);
    }

    /**
     * Executes the list command and returns the response message.
     *
     * @param taskList The task list containing the tasks.
     * @param ui       The user interface to interact with the user.
     * @param storage  The storage to save and load tasks.
     * @return The response message.
     * @throws PixelException If there is an error executing the command.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        String[] outputs = new String[taskList.size() + 1];
        outputs[0] = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            outputs[i + 1] = index + ". " + taskList.getTaskAtIndex(i);
        }
        return ui.getPixelResponse(outputs);
    }
}
