package derek.command;

import derek.exception.IncorrectCommandException;

/**
 * The {@code Command} class processes user input commands and determines the type of command
 * based on the input string. It also provides methods to check the validity of the command
 * and to extract the task details from the command.
 */
public class Command {
    private String command;

    /**
     * Constructs a {@code Command} object with the specified command string.
     *
     * @param command the command input by the user
     */

    public Command(String command) {
        this.command = command;
    }

    /**
     * Validates if the command is a consent response ('Y' or 'N').
     *
     * @throws IncorrectCommandException if the command is not 'Y' or 'N'
     */
    public void isConsent() throws IncorrectCommandException {
        if (!(this.command.equalsIgnoreCase("Y")
                || this.command.equalsIgnoreCase("N"))) {
            throw new IncorrectCommandException("Hate me just say! Answer my question RRRRAHHHH!!!");
        }
    }


    /**
     * Returns the command string.
     *
     * @return the command string
     */
    public String getCommand() {
        return this.command;
    }
}
