package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;
import tayoo.tasks.Task;

import java.util.Collections;
import java.util.List;

/**
 * The DeleteTaskCommand class contains all the sub-commands that should be executed when a Delete command is given by 
 * the user
 */
public class DeleteTaskCommand extends Command {

    private final List<Integer> taskNumbers;

    /**
     * Creates a new DeleteTask instance. When deleting tasks, it is most efficient to delete the task in descending
     * order because the tasks are stored in the tasklist as an ArrayList. The constructor therefore sorts the input
     * list of taskNumbers in descending order. The constructor also decrements each integer by one because the input
     * assumes a 1-based list, while the tasks are stored in a 0-based index.
     *
     * @param taskNumbers The list of indexes of tasks which are to be deleted. May not be sorted and is in 1-based
     *                    index
     */
    public DeleteTaskCommand(List<Integer> taskNumbers) {
        this.taskNumbers = taskNumbers;
        Collections.sort(taskNumbers, Collections.reverseOrder());

        taskNumbers.replaceAll(x -> x - 1);
    }

    private String deleteTaskCommandHelper(Tasklist tasklist, Storage storage) throws TayooException {
        StringBuilder toReturn = new StringBuilder();

        for (int taskToDelete: taskNumbers) {

            //guard conditions
            if (taskToDelete < 0) {
                throw new TayooException("Expected task number > 0");
            } else if (taskToDelete >= Tasklist.MAXIMUM_CAPACITY) {
                throw new TayooException("Expected task number < " + Tasklist.MAXIMUM_CAPACITY);
            } else if (taskToDelete >= tasklist.getSize()) {
                toReturn.append("Could not find task number ").append(taskToDelete + 1).append("\n");
                continue;
            }

            Task deletedTask = tasklist.deleteTask(taskToDelete);

            if (deletedTask != null) {
                storage.deleteTxt(taskToDelete);
                toReturn.append("Deleted: ").append(deletedTask.toString()).append("\n");
            } else {
                throw new TayooException("Exception occurred when deleting task");
            }
        }

        toReturn.append(tasklist.numberOfTasksLeft());

        return toReturn.toString();
    }

    /**
     * Executes the delete command and prints out all the tasks that have been deleted.
     *
     * @param tasklist that stores all the tasks.
     * @param ui ui representing the I/O system with the user.
     * @param storage represents the .txt file in which the tasks are stored
     * @throws TayooException if there is any error with executing the command (i.e. invalid input)
     */
    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        ui.printText(deleteTaskCommandHelper(tasklist, storage));
    }

    /**
     * Executes the delete command and returns all the tasks that have been deleted.
     *
     * @param tasklist that stores all the tasks.
     * @param ui ui representing the I/O system with the user.
     * @param storage represents the .txt file in which the tasks are stored
     * @return String response of the chatbot after executing this DeleteCommand
     * @throws TayooException if there is any error with executing the command (i.e. invalid input)
     */
    @Override
    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        return deleteTaskCommandHelper(tasklist, storage);
    }



    @Override
    public boolean isExit() {
        return false;
    }
}
