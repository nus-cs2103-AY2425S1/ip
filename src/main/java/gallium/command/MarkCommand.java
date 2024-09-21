package gallium.command;

import gallium.main.GalliumException;
import gallium.main.Storage;
import gallium.main.TaskList;
import gallium.main.Ui;

import gallium.task.Task;


/**
 * Represents a command to mark or unmark a task in the task list as done or not
 * done.
 */
public class MarkCommand extends Command {
    private String message;
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";

    /**
     * Constructs a MarkCommand with the specified task.
     * 
     * @param message The message containing the tasks.
     */
    public MarkCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the mark command to mark or unmark a task.
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
            boolean isMark = message.startsWith(MARK);
            String indexString = isMark? message.split("mark ")[1]: message.split("unmark ")[1];
            int index = Integer.parseInt(indexString);
            Task task = taskList.getTask(index - 1);
            task.setIsDone(isMark);
            ui.printMarkMessage(isMark, task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GalliumException("Please put a space after your command! \nExample: mark 10");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.showWrongIndex();
        } 
    }
}