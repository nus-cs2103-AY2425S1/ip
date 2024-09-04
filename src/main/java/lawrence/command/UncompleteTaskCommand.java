package lawrence.command;

import lawrence.database.TaskFileManager;
import lawrence.task.Task;
import lawrence.task.TaskList;
import lawrence.ui.UserInterface;

import java.io.IOException;

/**
 * Represents the user command to mark existing tasks as incomplete.
 */
public class UncompleteTaskCommand extends Command {
    private final String input;

    /**
     * Default constructor.
     *
     * @param input the user input associated with this command
     */
    public UncompleteTaskCommand(String input) {
        this.input = input;
    }

    /**
     * Finds the specified task and marks it as incomplete, then displays
     * the new status of the task to the user.
     * <p>
     * If information about the task to mark incomplete is invalid, the
     * method does nothing.
     * </p>
     *
     * @param taskList a list of tasks the command may operate
     *                 on
     * @param manager  a {@link TaskFileManager} instance that
     *                 the command may use when saving changes
     *                 made
     * @param ui       a {@link UserInterface} instance to
     *                 display possible messages to the user
     */
    @Override
    public void execute(TaskList taskList, TaskFileManager manager, UserInterface ui) {
        String[] inputComponents = input.split(" ", 2);
        if (inputComponents.length < 2) {
            ui.showMessage("Please specify the task you want to mark as incomplete.");
            return;
        }

        String rawTaskNumber = inputComponents[1];
        try {
            int taskNumber = Integer.parseInt(rawTaskNumber);
            Task incompleteTask = taskList.uncompleteTask(taskNumber);
            saveTasks(taskList, manager);

            ui.showMessage(String.format("Changed your mind? The task is set to incomplete:%n%s", incompleteTask));
        } catch (NumberFormatException e) {
            ui.showMessage("Please specify a number to select a task.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            ui.showMessage(String.format("%s Please try again.", e.getMessage()));
        } catch (IOException e) {
            ui.showMessage(String.format("Failed to mark task %s as incomplete. Please try again later.",
                    rawTaskNumber));
        }
    }
}
