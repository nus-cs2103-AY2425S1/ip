package commands;

import exceptions.ErrorMessageHandler;
import exceptions.InvalidDateException;
import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;
import task.Task;
import task.TaskList;

/**
 * Represents a command to add a new task to the task list.
 * This class extends the {@link Command} class and handles the creation and addition of tasks.
 */
public class AddTaskCommand extends Command {

    private String userInput;

    /**
     * Constructs an {@code AddTaskCommand} with the specified user input.
     *
     * @param userInput The user input containing the task description and details.
     */
    public AddTaskCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the add task command, creating a new task based on the user input and adding it to the task list.
     * If the task description is invalid or missing, appropriate error messages are displayed.
     *
     * @param taskList The task list to which the new task will be added.
     */
    @Override
    public String execute(TaskList taskList) {
        StringBuilder result = new StringBuilder();
        try {
            Task newTask = Task.createTask(userInput);
            taskList.addTask(newTask);

            // Construct the message
            result.append("Alrighty! The following task has been added:\n ")
                    .append(newTask).append("\n")
                    .append("Oh my goodness you have ").append(taskList.getSize()).append(" tasks remaining\n");

            taskList.writeToStorage();
        } catch (NoTaskDescriptionException e) {
            return ErrorMessageHandler.getNoTaskDescriptionMessage();
        } catch (InvalidTaskException e) {
            return ErrorMessageHandler.getInvalidTaskMessage();
        } catch (InvalidDateException e) {
            return ErrorMessageHandler.getInvalidDateMessage();
        }

        return result.toString();
    }
}
