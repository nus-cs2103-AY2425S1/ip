package lawrence.command;

import lawrence.database.TaskFileManager;
import lawrence.task.Task;
import lawrence.task.TaskList;
import lawrence.ui.UserInterface;

import java.io.IOException;

/**
 * Represents the user command to mark existing tasks as complete.
 */
public class CompleteTaskCommand extends Command {
    private final String input;

    /**
     * Default constructor.
     *
     * @param input the user input associated with this command
     */
    public CompleteTaskCommand(String input) {
        this.input = input;
    }

    /**
     * Finds the specified task and marks it as complete.
     * <p>
     * Displays the result of the execution to the user via text messages.
     * </p>
     * <p>
     * If the details of the task to mark complete cannot be parsed from
     * user input, the method does nothing.
     * </p>
     *
     * @param taskList a list of tasks the command may operate
     *                 on
     * @param manager  a {@link TaskFileManager} instance that
     *                 the command may use when saving changes
     *                 made
     * @param ui       the {@link UserInterface} instance to
     *                 display possible messages to the user
     */
    @Override
    public void execute(TaskList taskList, TaskFileManager manager, UserInterface ui) {
        String[] inputComponents = input.split(" ", 2);
        if (inputComponents.length < 2) {
            ui.showMessage("Please specify the task you want to mark as complete.");
            return;
        }

        String rawTaskNumber = inputComponents[1];
        try {
            int taskNumber = Integer.parseInt(rawTaskNumber);
            Task completeTask = taskList.completeTask(taskNumber);
            saveTasks(taskList, manager);
            ui.showMessage(
                    String.format("I've marked the task as complete:%n%s", completeTask));
        } catch (NumberFormatException e) {
            ui.showMessage("Please specify a number to select a task.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            ui.showMessage(String.format("%s Please try again.", e.getMessage()));
        } catch (IOException e) {
            ui.showMessage(String.format("Failed to mark task %s as complete. Please try again later.", rawTaskNumber));
        }
    }
}
