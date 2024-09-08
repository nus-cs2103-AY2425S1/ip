package jeff.command;

import jeff.exception.JeffException;
import jeff.parser.Parser;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;

/**
 * Represents an "Add task" command.
 */
public abstract class AddCommand extends Command {
    /**
     * Constructor for AddCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public AddCommand(String input) {
        super(input);
    }

    /**
     * Returns the task description.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        String[] taskParts = this.getInput().split(" ", 2);
        return taskParts[1];
    }

    /**
     * Returns the response by the chatbot Jeff when a task is added.
     *
     * @param task Task that is added to the task list.
     * @param tasks Task list.
     * @return Response when a task is added.
     */
    public String getResponse(Task task, TaskList tasks) {
        return Parser.addSpaceInFrontOfEachLine(
                String.format(
                        "Got it. I've added this task:\n   %s\nNow you have %s tasks in the list.",
                        task.toString(),
                        tasks.size()
                )
        );
    }

    /**
     * Returns the string representation of the response by the chatbot Jeff when a task is added.
     *
     * @param tasks Task list.
     * @param storage Place to get and write the task list to the tasks text file.
     * @return String representation of the response.
     * @throws JeffException if the user's input is in the wrong format or the description of the task is empty.
     */
    @Override
    public abstract String execute(TaskList tasks, Storage storage) throws JeffException;
}
