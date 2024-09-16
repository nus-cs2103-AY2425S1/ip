package bottleopener.command;

import bottleopener.ui.Ui;

/**
 * Represents a command that is executed when there is an error in
 * parsing or recognizing user input.
 * This command is used to handle invalid commands or incorrect command
 * formats by providing feedback to the user.
 */
public class ErrorCommand implements Command {

    /**
     * Constructs an ErrorCommand object.
     */
    public ErrorCommand() {

    }

    /**
     * Executes the error command by displaying an error message
     * related to incorrect command formatting.
     *
     * @return A string containing the error message for invalid command formats.
     */
    @Override
    public String runCommand() {
        return Ui.showCommandFormatError();
    }

}
