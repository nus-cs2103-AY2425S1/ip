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
 * Represents a command to unmark a task in the task list as not done.
 * The task to be unmarked is identified by its position in the list.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs an UnmarkCommand with the specified activity status and input.
     *
     * @param isActive boolean indicating whether this command is active.
     * @param input the input associated with this command, typically including the task number to unmark.
     */
    public UnmarkCommand(boolean isActive, String input) {
        super(isActive, input);
    }

    /**
     * Executes the UnmarkCommand, which marks a task in the task list as not done based on the provided input.
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

        taskList.get(taskNo).setStatusIcon(false);

        ui.showLine();
        System.out.println("OK, I've marked this task as not done yet:\n" + taskList.get(taskNo));
        ui.showLine();

        ui.setOutput("OK, I've marked this task as not done yet:\n" + taskList.get(taskNo));
    }
}
