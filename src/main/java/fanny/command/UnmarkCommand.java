package fanny.command;

import fanny.task.TaskList;
import fanny.ui.Ui;

/**
 * Represents the command that handles the "unmark" prompt.
 */
public class UnmarkCommand extends Command {

    /** The input from the user in the form of string. */
    private String input;

    /**
     * Constructs a {@code UnmarkCommand} with the user input.
     *
     * @param input The user's input.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the "unmark" command by marking the task specified in the
     * user input as not complete.
     *
     * @param list The current list of tasks.
     * @param ui The UI object to interact with the user.
     * @return The message to be displayed after executing the command.
     */
    @Override
    public String executeCmd(TaskList list, Ui ui) {
        String message = "";

        try {
            ui.showHorizontalLine();
            int taskId = Integer.parseInt(input);
            message = ui.showUnmarkTaskMsg(taskId, list);
        } catch (NumberFormatException e) {
            message = ui.showMessage("The index must be an integer.");
        } catch (IllegalArgumentException e) {
            message = ui.showMessage(e.getMessage());
        } finally {
            ui.showHorizontalLine();
        }

        return message;
    }

    /**
     * Indicates that executing this command should not exit the application.
     *
     * @return {@code false}, indicating that the application should not exit.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }

}
