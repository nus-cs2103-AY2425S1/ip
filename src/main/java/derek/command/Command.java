package derek.command;

import derek.IncorrectCommandException;

/**
 * The Command class processes user input commands and determines the type of command
 * based on the input string. It also provides methods to check the validity of the command
 * and to extract the task details from the command.
 */
public class Command {
    private String command;

    public Command (String command) {
        this.command = command;
    }

    public void isConsent() throws IncorrectCommandException {
        if (!(this.command.equalsIgnoreCase("Y") || this.command.equalsIgnoreCase("N"))) {
            throw new IncorrectCommandException("Hate me just say! Answer my question RRRRAHHHH!!!");
        }
    }

    public String getCommand() {
        return this.command;
    }
}
