package jeff.command;

import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;

/**
 * Represents a "Mark task as not yet done" command.
 */
public class UnmarkCommand extends Command {
    /**
     * Constructor for UnmarkCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public UnmarkCommand(String input) {
        super(input);
    }

    /**
     * Returns the string representation of the response by the chatbot Jeff when a task is marked as not done yet.
     * Gets the target task from the task list and mark it as not done yet.
     *
     * @param tasks Task list.
     * @param storage Place to get and write the task list to the tasks text file.
     * @return String representation of the response.
     * @throws JeffException if the user's input is in the wrong format or if the task has not been marked as done.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        Task targetTask = tasks.getTaskByCommand(this.getInput(), "unmark ");

        if (!targetTask.isDone()) {
            throw new JeffException("This task has already been marked as not done yet!");
        }

        targetTask.markAsNotDone();

        return Parser.addSpaceInFrontOfEachLine(
                "OK, I've marked this task as not done yet:\n   " + targetTask.toString()
        );
    }
}
