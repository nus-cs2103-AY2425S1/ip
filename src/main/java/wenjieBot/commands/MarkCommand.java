package wenjieBot.commands;

import wenjieBot.Storage;
import wenjieBot.Ui;
import wenjieBot.TaskList;
import wenjieBot.tasks.Task;
import wenjieBot.exceptions.DukeException;
import wenjieBot.exceptions.NoNumberInputtedException;
import wenjieBot.exceptions.OutOfBoundsException;

import java.util.ArrayList;

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
     * @throws DukeException if there is an error during execution, such as no task number provided or an out-of-bounds task number.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parts = getInput().split(" ");
        ArrayList<Task> taskList = tasks.getTasks();

        if (parts.length <= 1) {
            throw new NoNumberInputtedException();
        }

        int taskNo = Integer.parseInt(parts[1]) - 1;

        if (taskNo + 1 > taskList.size()) {
            throw new OutOfBoundsException();
        }

        taskList.get(taskNo).setStatusIcon(true);

        ui.showLine();
        System.out.println("Nice! I've marked this task as done:\n" + taskList.get(taskNo));
        ui.showLine();
    }
}
