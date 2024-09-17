package fanny.command;

import fanny.task.TaskList;
import fanny.ui.Ui;

/**
 * Represents the command that handles the "delete" prompt.
 */
public class DeleteCommand extends Command {

    /** The input from the user in the form of string. */
    private String input;

    /**
     * Constructs a {@code DeleteCommand} with the user input.
     *
     * @param input The user's input.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the "delete" command by removing the task specified
     * in the user input.
     *
     * @param list The current list of tasks.
     * @param ui The UI object to interact with the user.
     * @return The delete message to be displayed.
     */
    @Override
    public String executeCmd(TaskList list, Ui ui) {
        int taskId = Integer.parseInt(input);
        String message = "";

        ui.showHorizontalLine();
        message = ui.showDeleteTaskMsg(taskId, list);
        ui.showHorizontalLine();

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
