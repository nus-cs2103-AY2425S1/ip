package krona.command;

import krona.storage.Storage;
import krona.task.TaskList;
import krona.ui.Ui;

/**
 * Represents a command that is invalid or not recognized by the Krona chatbot.
 */
public class InvalidCommand extends Command {
    private final String errorMessage;

    /**
     * Constructs an InvalidCommand with a specific error message.
     *
     * @param errorMessage The error message to display.
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the command by displaying an error message indicating that the command is not recognized.
     *
     * @param tasks   The task list that the command operates on (not used in this command).
     * @param ui      The UI component that handles interactions with the user.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();

        // If there is an error message, use it; otherwise, use the default message
        if (errorMessage != null && !errorMessage.isEmpty()) {
            output.append(errorMessage);
        } else {
            output.append("Invalid Command! Use \"help\" to see available commands");
        }

        // Send the combined message to the UI
        ui.setCombinedMessage(output.toString());
    }
}
