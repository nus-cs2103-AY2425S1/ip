package pixel.command;

import pixel.PixelException;
import pixel.Storage;
import pixel.Ui;
import pixel.task.TaskList;

/**
 * Represents a command to add a task to the task list.
 */
public class SortCommand extends Command {
    private final String keyword;

    public SortCommand(String keyWord) throws PixelException {
        super(false);
        String sanitisedKeyWord = keyWord.trim().toUpperCase();
        if (sanitisedKeyWord.equals("NAME")) {
            this.keyword = "name";
        } else if (sanitisedKeyWord.equals("DEADLINE")) {
            this.keyword = "deadline";
        } else if (sanitisedKeyWord.equals("EVENT")) {
            this.keyword = "event";
        } else {
            throw new PixelException("Invalid sort keyword. Please enter 'name' or 'deadline' or 'event'.");
        }
    }

    /**
     * Executes the add command by adding the task to the task list.
     *
     * @param taskList The task list to add the task to.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving the task list.
     * @throws PixelException If there is an error executing the command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        // taskList.addTask(this.task);
        // ui.pixelSays("Got it. I've added this task:", " " + this.task, "Now you have
        // " + taskList.size()
        // + " tasks in the list.");
        switch (this.keyword) {
        case "name":
            taskList.sortByName();
            break;
        case "deadline":
            taskList.sortByDeadline();
            break;
        case "event":
            taskList.sortByEvent();
            break;
        default:
            throw new PixelException("Invalid sort keyword. Please enter 'name' or 'deadline' or 'event'.");
        }
        ui.pixelSays("Got it. I have sorted your tasks!");
    }

    /**
     * Executes the add command by adding the task to the task list and returns a
     * response.
     *
     * @param taskList The task list to add the task to.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving the task list.
     * @return The response to be displayed to the user.
     * @throws PixelException If there is an error executing the command.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        this.execute(taskList, ui, storage);
        return "Got it. I have sorted your tasks!";
    }
}
