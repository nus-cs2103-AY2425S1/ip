package lawrence.command;

import java.io.IOException;

import lawrence.database.TaskFileManager;
import lawrence.task.Task;
import lawrence.task.TaskList;
import lawrence.ui.UserInterface;

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
    public UncompleteTaskCommand(CommandType type, String input) {
        super(type);
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
     * @param tasks a list of tasks the command may operate
     *              on
     * @param manager a {@link TaskFileManager} instance that
     *                the command may use when saving changes
     *                made
     * @param ui a {@link UserInterface} instance to display
     *           possible messages to the user
     */
    @Override
    public void execute(TaskList tasks, TaskFileManager manager, UserInterface ui) {
        String[] inputComponents = input.split(" ", 2);
        if (inputComponents.length < 2) {
            this.response = "Please specify the task you want to mark as incomplete.";
            return;
        }

        String rawTaskNumber = inputComponents[1];
        try {
            int taskNumber = Integer.parseInt(rawTaskNumber);
            Task incompleteTask = tasks.uncompleteTask(taskNumber);
            saveTasks(tasks, manager);

            this.response = String.format("Changed your mind? The task is set to incomplete:%n%s", incompleteTask);
        } catch (NumberFormatException e) {
            this.response = "Please specify a number to select a task.";
        } catch (IllegalArgumentException | IllegalStateException e) {
            this.response = String.format("%s Please try again.", e.getMessage());
        } catch (IOException e) {
            this.response = String.format("Failed to mark task %s as incomplete. Please try again later.",
                    rawTaskNumber);
        }
    }
}
