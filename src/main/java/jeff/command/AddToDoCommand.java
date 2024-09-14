package jeff.command;

import jeff.exception.JeffException;
import jeff.storage.Storage;
import jeff.task.Task;
import jeff.task.TaskList;
import jeff.task.ToDoTask;

/**
 * Represents an "Add to-do task" command.
 */
public class AddToDoCommand extends AddCommand {
    private static final String WRONG_FORMAT_ERROR = "The format is wrong! It should be \"todo(or t) xx\"";

    /**
     * Constructor for AddToDoCommand Class.
     * Stores the user's input.
     *
     * @param input User's input.
     */
    public AddToDoCommand(String input) {
        super(input);
    }

    /**
     * Checks if the format of the user input is wrong or not.
     *
     * @return true if the user input is in the wrong format and false otherwise.
     */
    private boolean isFormatWrong() {
        return !this.getInput().matches("todo .+") && !this.getInput().matches("t .+");
    }

    /**
     * Returns a newly created to-do task based on the user input.
     *
     * @return To-do task based on user input.
     */
    private ToDoTask createTask() {
        assert !this.isFormatWrong() : "To-Do task should not be in the wrong format when creating the task";

        String taskDescription = this.getTaskDescription();
        return new ToDoTask(taskDescription);
    }

    /**
     * Returns the string representation of the response by the chatbot Jeff
     * when a to-do task is added to the task list.
     *
     * @param tasks Task list.
     * @param storage Place to get and write the task list to the tasks text file.
     * @return String representation of the response.
     * @throws JeffException if the description of the task is empty.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JeffException {
        if (this.isFormatWrong()) {
            throw new JeffException(WRONG_FORMAT_ERROR);
        }

        Task targetTask = this.createTask();
        assert targetTask != null : "Target task should not be null";

        tasks.addTask(targetTask);
        assert tasks.contains(targetTask) : "Task list should contain the newly added task";

        storage.updateTaskListInDatabase(tasks);

        return this.getResponse(targetTask, tasks);
    }
}
