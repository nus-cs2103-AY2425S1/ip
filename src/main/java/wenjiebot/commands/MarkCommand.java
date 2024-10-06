package wenjiebot.commands;

import java.util.ArrayList;

import wenjiebot.Storage;
import wenjiebot.TaskList;
import wenjiebot.Ui;
import wenjiebot.exceptions.InvalidIndexException;
import wenjiebot.exceptions.InvalidNumberException;
import wenjiebot.exceptions.NoNumberInputtedException;
import wenjiebot.exceptions.OutOfBoundsException;
import wenjiebot.exceptions.WenJieException;
import wenjiebot.tasks.Task;


/**
 * Represents a command to mark a task as completed in the task list.
 * The task to be marked is identified by its position in the list.
 */
public class MarkCommand extends Command {

    /**
     * Constructs a MarkCommand with the specified activity status and input.
     *
     * @param isActive boolean indicating whether this command is active.
     * @param input the input associated with this command, typically including the task number to mark as done.
     */
    public MarkCommand(boolean isActive, String input) {
        super(isActive, input);
    }

    /**
     * Executes the MarkCommand, which marks the specified task as completed based on the provided input.
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
        int taskNo;
        try {
            taskNo = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
        if (taskNo + 1 > taskList.size()) {
            throw new OutOfBoundsException();
        }
        if (taskNo < 0) {
            throw new InvalidIndexException();
        }

        taskList.get(taskNo).setStatusIcon(true);

        assert(taskList.get(taskNo).isDone() == true);

        ui.showLine();
        System.out.println("UWU! I've marked this task as done:\n" + taskList.get(taskNo));
        ui.showLine();

        ui.setOutput("UWU! I've marked this task as done:\n" + taskList.get(taskNo));
    }
}
