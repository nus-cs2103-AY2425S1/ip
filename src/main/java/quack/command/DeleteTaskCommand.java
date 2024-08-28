package quack.command;

import quack.Quack;
import quack.TaskList;
import quack.Storage;
import quack.Ui;
import quack.exception.InvalidIndexException;
import quack.tasks.Task;

/**
 * This class is responsible for deleting tasks in the task list.
 */
public class DeleteTaskCommand extends Command{

    /**
     * Creates a DeleteTaskCommand object.
     */
    public DeleteTaskCommand() {

    }

    @Override
    public void execute(Quack quack, TaskList taskList, Storage storage, Ui ui) {
        
        Command listCommand = new ListCommand();
        listCommand.execute(quack, taskList, storage, ui);

        if (taskList.getLength() != 0) {
            try {
                int index = ui.requestIndexFromUser("delete");
                Task removedTask = taskList.deleteTask(index);
                ui.printUpdateSuccessfulMessage(removedTask, "delete", taskList);
            } catch (InvalidIndexException invalidIdxError) {
                ui.printExceptionMessage(invalidIdxError);
            } catch (IndexOutOfBoundsException indexError) {
               ui.printExceptionMessage(indexError);;
            }
        }
    }
}
