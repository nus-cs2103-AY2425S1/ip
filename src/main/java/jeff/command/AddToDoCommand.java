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
     * Checks if the description for the to-do task inputted by the user is empty or not.
     *
     * @return true if the user input does not have a description and false otherwise.
     */
    private boolean isDescriptionEmpty() {
        return !this.getInput().matches("todo .+");
    }

    /**
     * Returns a newly created to-do task based on the user input.
     *
     * @return To-do task based on user input.
     */
    private ToDoTask createTask() {
        assert !this.isDescriptionEmpty() : "To-do task description should not be empty when creating the task";

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
        if (this.isDescriptionEmpty()) {
            throw new JeffException("Sorry, the description of a todo cannot be empty!");
        }

        Task targetTask = this.createTask();
        tasks.addTask(targetTask);
        storage.updateTaskListInDatabase(tasks);

        return this.getResponse(targetTask, tasks);
    }
}
