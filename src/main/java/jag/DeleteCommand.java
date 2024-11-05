package jag;

import java.io.IOException;

/**
 * Represents and instance of a DeleteCommand to delete a task
 * from an instance of the TaskList and to call upon
 * the Ui instance to display the appropriate message
 */
public class DeleteCommand extends Command {

    /**
     * Executes the command such that the required task gets deleted
     * from the instance of TaskList
     *
     * @param tasks Represents instance of the TaskList to be accessed
     *              such that the right task is deleted
     * @param ui Represents the Ui instance for user interaction and to
     *           break down the command input to get the right index
     *           of the task to be deleted in the TaskList instance
     * @param storage To update the output file storage after task
     *                has been deleted
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // jag.Task
        int index = ui.getDeleteIndex();

        // jag.Ui response + deletion
        Task task = tasks.getTask(index - 1);
        ui.deleteResponse(task, tasks.size() - 1);
        tasks.deleteTask(index - 1);

        // jag.Storage
        try {
            storage.write(tasks);
        } catch (IOException io) {
            System.out.println("Error in writing history" + io.getMessage());
        }
    }

    /**
     * Returns false so that Jag.java does not exit for loop
     *
     * @return a default false so the run() in Jag.java does not exit
     *              the while loop
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
