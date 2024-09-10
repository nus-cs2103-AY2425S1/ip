package quack.command;

import quack.exception.InvalidCommandException;
import quack.exception.InvalidIndexException;
import quack.tasks.Task;
import quack.util.TaskList;
import quack.util.Ui;

/**
 * This class is responsible for removing the tag from a task.
 */
public class RemoveTagCommand extends Command {

    /** List to store all tasks by Quack */
    private TaskList taskList;
    /** Ui to handle all user display interactions */
    private Ui ui;

    /**
     * Creates a UnTagCommand object.
     * @param taskList A list that stores all the tasks tracked by Quack.
     * @param ui The ui object that handles user interface requests.
     */
    public RemoveTagCommand(TaskList taskList, Ui ui) {
        super();
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public void prompt() {
        this.listTasks();
        this.checkEmptyList();
        ui.requestIndexFromUser("un-tag");
    }

    @Override
    public void execute(String input) {

        assert(input != null);

        try {
            int index = Integer.parseInt(input);
            Task task = this.taskList.updateTag(index, "removeTag");
            ui.printUpdateSuccessfulMessage(task, "un-tag", taskList);
        } catch (NumberFormatException invalidIdxError) {
            ui.printExceptionMessage(new InvalidIndexException(input));
        } catch (IndexOutOfBoundsException indexError) {
            ui.printExceptionMessage(indexError);
        } catch (InvalidCommandException commandError) {
            ui.printExceptionMessage(commandError);
        } finally {
            this.completeCommand();
        }
    }

    /**
     * Lists all the tasks currently in the task list.
     */
    private void listTasks() {
        Command listCommand = new ListCommand(taskList, ui);
        listCommand.prompt();
    }

    /**
     * Checks if the task list is empty.
     * <p>
     * If the task list is empty then set the status of the command to be completed
     * since there is nothing to delete.
     */
    private void checkEmptyList() {
        if (taskList.getLength() == 0) {
            this.completeCommand();
            return;
        }
    }
}
