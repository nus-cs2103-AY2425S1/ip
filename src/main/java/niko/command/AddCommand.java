//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package niko.command;

import niko.common.NikoException;
import niko.main.Storage;
import niko.main.Ui;
import niko.task.Task;
import niko.task.TaskList;

/**
 * Represents a command to add a task to the task list.
 * This command will add the specified task, update the UI with the added task,
 * and then write the updated task list to the storage.
 */
public class AddCommand extends Command {

    /** The task to be added to the task list. */
    private Task task;

    /** A string representation of the tasks to be written to storage. */
    private String readyToWrite;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command, which adds the task to the task list,
     * updates the UI with the new task, and writes the updated task list to storage.
     *
     * @param tasks   The task list to which the task will be added.
     * @param ui      The UI to be updated with the new task.
     * @param storage The storage where the updated task list will be written.
     * @return
     * @throws NikoException If there is an error during the writing process to the storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NikoException {
        tasks.addTask(this.task);
        this.readyToWrite = tasks.getTasks().toString();
        storage.write(this.readyToWrite.substring(1, this.readyToWrite.length() - 1));
        return ui.showAddTaskMessage(this.task, tasks.getTaskCount());
    }
}
