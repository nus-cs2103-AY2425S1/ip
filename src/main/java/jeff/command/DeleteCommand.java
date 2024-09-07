package jeff.command;

import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;

/**
 * Represents a "Delete task" command.
 */
public class DeleteCommand extends Command {
    /**
     * Constructor for DeleteCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Returns the string representation of the response by the chatbot Jeff when a task is deleted.
     * Gets the task to be deleted from the task list and delete it.
     *
     * @param tasks Task list.
     * @param storage Place to get and write the task list to the tasks text file.
     * @return String representation of the response.
     * @throws JeffException if the user's input is in the wrong format or if the task number does not exist.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        Task targetTask = tasks.getTaskByCommand(this.getInput(), "delete ");
        tasks.deleteTask(targetTask);
        storage.updateTaskListInDatabase(tasks);

        return Parser.addSpaceInFrontOfEachLine(
                String.format(
                        "Noted. I've removed this task:\n   %s\nNow you have %d tasks in the list.",
                        targetTask.toString(),
                        tasks.size()
                )
        );
    }
}
