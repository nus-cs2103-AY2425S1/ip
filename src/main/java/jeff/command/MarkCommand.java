package jeff.command;

import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;

/**
 * Represents a "Mark task as done" command.
 */
public class MarkCommand extends Command {
    /**
     * Constructor for MarkCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public MarkCommand(String input) {
        super(input);
    }

    /**
     * Returns the string representation of the response by the chatbot Jeff when a task is marked as done.
     * Gets the target task from the task list and mark it as done.
     *
     * @param tasks Task list.
     * @param storage Place to get and write the task list to the tasks text file.
     * @return String representation of the response.
     * @throws JeffException if the user's input is in the wrong format or if the task has already been marked as done.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        assert tasks != null : "Task list should not be null";
        assert storage != null : "Storage should not be null";

        // Get the task from the taskList
        Task targetTask = tasks.getTask(this.getInput(), "mark ");
        assert targetTask != null : "Target task should not be null";

        // Check if the task has been done or not
        if (targetTask.isDone()) {
            // Tell the user that the task is already marked as done
            throw new JeffException("This task has already been marked as done!");

        } else {
            // Mark the task as done
            targetTask.markAsDone();
            assert targetTask.isDone() : "Target task should be marked as done";

            // Update database
            storage.writeTaskList(tasks);

            // Return the response
            return Parser.prettyText("OK, I've marked this task as done:\n   " + targetTask.toString());

        }
    }
}
