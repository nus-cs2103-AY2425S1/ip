package Commands;

import Task.TaskList;
import Task.Task;
import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;

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
    public void execute(TaskList taskList) {
        try {
            Task newTask = Task.createTask(userInput);
            taskList.addTask(newTask);
            System.out.println("----------------\n" +
                    "Alrighty! The following task has been added:\n " +
                    newTask + "\n" +
                    "Oh my goodness you have " + taskList.getSize() + " tasks remaining\n" +
                    "----------------\n");
        } catch (NoTaskDescriptionException e) {
            System.out.println("Wah, no description then I record what?");
        } catch (InvalidTaskException e) {
            System.out.println("THIS IS NOT A VALID TASK LAH");
        }
    }
}
