package quack.command;

import quack.exception.FailedUpdateException;
import quack.exception.InvalidIndexException;
import quack.tasks.Task;
import quack.util.TaskList;
import quack.util.Ui;

/**
 * This class is responsible for updating of tasks in the task list.
 */
public class UpdateTaskCommand extends Command {

    /** List to store all tasks by Quack */
    private TaskList taskList;
    /** Ui to handle all user display interactions */
    private Ui ui;
    /** Update command that the user wants to execute */
    private String command;

    /**
     * Creates a UpdateTaskCommand object.
     * @param command What the user wants to update.
     * @param taskList A list that stores all the tasks tracked by Quack.
     * @param ui The ui object that handles user interface requests.
     */
    public UpdateTaskCommand(String command, TaskList taskList, Ui ui) {
        super();
        this.command = command;
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public void prompt() {

       this.listTasks();
       this.checkEmptyList();
        ui.requestIndexFromUser(command);
    }

    @Override
    public void execute(String input) {

        try {
            int index = Integer.parseInt(input);
            Task task = taskList.updateTask(index, this.command);
            ui.printUpdateSuccessfulMessage(task, command, taskList);
        } catch (NumberFormatException invalidIdxError) {
            ui.printExceptionMessage(new InvalidIndexException(input));
        } catch (IndexOutOfBoundsException indexError) {
            ui.printExceptionMessage(indexError);
        } catch (FailedUpdateException failUpdateError) {
            ui.printExceptionMessage(failUpdateError);
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
