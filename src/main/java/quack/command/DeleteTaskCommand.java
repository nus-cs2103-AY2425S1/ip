package quack.command;

import quack.TaskList;
import quack.Ui;
import quack.exception.InvalidIndexException;
import quack.tasks.Task;

/**
 * This class is responsible for deleting tasks in the task list.
 */
public class DeleteTaskCommand extends Command {

    /** List to store all tasks by Quack */
    private TaskList taskList;
    /** Ui to handle all user display interactions */
    private Ui ui;

    /**
     * Creates a DeleteTaskCommand object.
     * @param taskList A list that stores all the tasks tracked by Quack.
     * @param ui The ui object that handles user interface requests.
     */
    public DeleteTaskCommand(TaskList taskList, Ui ui) {
        super();
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public void prompt() {

        Command listCommand = new ListCommand(taskList, ui);
        listCommand.prompt();

        if (taskList.getLength() == 0) {
            this.completeCommand();
            return;
        }

        ui.requestIndexFromUser("delete");
    }

    @Override
    public void execute(String input) {

        // Ensures that the input is not null as if it is null then Quack is not getting the input from the user
        assert(input != null);

        try {
            // Convert the input into a integer
            int index = Integer.parseInt(input);
            Task task = taskList.deleteTask(index);
            ui.printUpdateSuccessfulMessage(task, "delete", taskList);
        } catch (NumberFormatException invalidIdxError) {
            ui.printExceptionMessage(new InvalidIndexException(input));
        } catch (IndexOutOfBoundsException indexError) {
            ui.printExceptionMessage(indexError);
        } finally {
            this.completeCommand();
        }
    }
}
