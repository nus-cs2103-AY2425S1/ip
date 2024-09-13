package lutodo.commands;

import lutodo.parser.Parser;
import lutodo.tasklist.TaskList;
import lutodo.storage.Storage;
import lutodo.tasks.Task;

import static java.lang.Integer.parseInt;

/**
 * Represents the command of deleting a task in the task list.
 */
public class DeleteCommand extends Command{
    private int index = -1;
    private String fullCommand;

    /**
     * Constructs a DeleteCommand object with the index of the task to be deleted.
     *
     * @param fullCommand The command message sent by user.
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Deletes a task in the task list, informs the user and saves the new task list.
     *
     * @param tasks   The TaskList that has some task to be deleted.
     * @param storage The Storage object used to save the new task list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        assert fullCommand != null : "task message cannot be null";
        if (Parser.splitTaskInfo(fullCommand).length <= 1) {
            System.out.println("You are not telling me which task should I delete :-(");
        }
        this.index = parseInt(Parser.splitTaskInfo(fullCommand)[1]) - 1;
        try {
            System.out.println("Noted. I've removed this task:\n" + tasks.get(index)
                    + "\nNow you have " + (tasks.size() - 1) + " tasks in the list.");
            tasks.deleteTask(index);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("The task you want to delete is not in task list, please try again.");
        }
        storage.save(tasks);
    }

    /**
     * Deletes a task in the task list, informs the user and saves the new task list.
     *
     * @param tasks   The TaskList that has some task to be deleted.
     * @param storage The Storage object used to save the new task list.
     */
    @Override
    public String executeAndRespond(TaskList tasks, Storage storage) {
        assert fullCommand != null : "task message cannot be null";
        if (Parser.splitTaskInfo(fullCommand).length <= 1) {
            return  "You are not telling me which task should I delete :-(";
        }
        this.index = parseInt(Parser.splitTaskInfo(fullCommand)[1]) - 1;
        try {
            Task removedTask = tasks.get(index);
            tasks.deleteTask(index);
            storage.save(tasks);
            return "Noted. I've removed this task:\n" + removedTask
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch(IndexOutOfBoundsException e) {
            return "The task you want to delete is not in task list, please try again.";
        }
    }

    /**
     * Returns false since this type of command is not exit command.
     *
     * @return whether this is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Represents the command with a String containing basic information of this command.
     *
     * @return a String containing command type and the index of the task to be deleted.
     */
    @Override
    public String toString() {
        return "DeleteCommand: " + index;
    }
}
