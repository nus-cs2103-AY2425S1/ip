package quack.command;

import quack.TaskList;
import quack.Ui;
import quack.tasks.Task;
import quack.exception.InvalidIndexException;
import quack.exception.FailedUpdateException;

/**
 * This class is responsible for updating of tasks in the task list.
 */
public class UpdateTaskCommand extends Command{
    
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
    public UpdateTaskCommand (String command, TaskList taskList, Ui ui) {
        this.command = command;
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public void execute() {

        Command listCommand = new ListCommand(taskList, ui);
        listCommand.execute();

        if (taskList.getLength() != 0) {
            try {
                int index = ui.requestIndexFromUser(this.command);
                Task task = taskList.updateTask(index, this.command);
                ui.printUpdateSuccessfulMessage(task, command, taskList);
            } catch (InvalidIndexException invalidIdxError) {
                ui.printExceptionMessage(invalidIdxError);
            } catch (IndexOutOfBoundsException indexError) {
               ui.printExceptionMessage(indexError);;
            } catch (FailedUpdateException failUpdateError) {
                ui.printExceptionMessage(failUpdateError);
            }
        }
    }
}
