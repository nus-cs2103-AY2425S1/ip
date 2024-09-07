package lawrence.command;

import java.io.IOException;

import lawrence.database.TaskFileManager;
import lawrence.task.Task;
import lawrence.task.TaskList;
import lawrence.ui.UserInterface;

/**
 * Represents the user command to delete existing tasks from the list.
 */
public class DeleteTaskCommand extends Command {
    private String input;

    /**
     * Default constructor.
     *
     * @param input the user input associated with this command
     */
    public DeleteTaskCommand(String input) {
        this.input = input;
    }

    /**
     * Finds the specified task and deletes it from the list, then
     * displays the new list after deletion to the user.
     * <p>
     * If information about the task to be deleted is invalid,
     * the method does nothing.
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
            ui.showMessage("Please specify the task you want to delete.");
            return;
        }

        String rawTaskNumber = inputComponents[1];
        try {
            int taskNumber = Integer.parseInt(rawTaskNumber);
            Task deletedTask = tasks.deleteTask(taskNumber);
            saveTasks(tasks, manager);

            ui.showMessage(String.format("Task:%n%s%nhas been deleted.", deletedTask));
        } catch (NumberFormatException e) {
            ui.showMessage("Please specify an integer to select a task.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            ui.showMessage(String.format("%s Please try again.", e.getMessage()));
        } catch (IOException e) {
            ui.showMessage(String.format("Failed to delete task %s from the list. Please try again.", rawTaskNumber));
        }
    }

}
