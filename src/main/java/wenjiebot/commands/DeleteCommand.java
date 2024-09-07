package wenjiebot.commands;

import java.util.ArrayList;

import wenjiebot.Storage;
import wenjiebot.TaskList;
import wenjiebot.Ui;
import wenjiebot.exceptions.WenJieException;
import wenjiebot.exceptions.NoNumberInputtedException;
import wenjiebot.exceptions.OutOfBoundsException;
import wenjiebot.tasks.Task;


/**
 * Represents a command to delete a task from the task list.
 * The task to be deleted is identified by its position in the list.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand with the specified activity status and input.
     *
     * @param isActive boolean indicating whether this command is active.
     * @param input the input associated with this command, typically including the task number to delete.
     */
    public DeleteCommand(boolean isActive, String input) {
        super(isActive, input);
    }

    /**
     * Executes the DeleteCommand, which removes a task from the task list based on the provided input.
     * If the input is invalid or the task number is out of bounds, it throws an appropriate exception.
     *
     * @param tasks the TaskList that contains all the tasks.
     * @param ui the Ui used for interaction with the user.
     * @param storage the Storage used to store and retrieve tasks.
     * @throws WenJieException if there is an error during execution, such as no task number provided or an
     *      out-of-bounds task number.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WenJieException {
        String[] parts = getInput().split(" ");
        ArrayList<Task> taskList = tasks.getTasks();

        if (parts.length <= 1) {
            throw new NoNumberInputtedException();
        }

        int taskNo = Integer.parseInt(parts[1]) - 1;

        if (taskNo + 1 > taskList.size()) {
            throw new OutOfBoundsException();
        }

        Task taskToRemove = taskList.get(taskNo);
        taskList.remove(taskNo);

        ui.showLine();
        System.out.println(
                "Noted. I've removed this task:\n"
                        + taskToRemove + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list.");
        ui.showLine();

        ui.setOutput("Noted. I've removed this task:\n"
                + taskToRemove + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }
}
