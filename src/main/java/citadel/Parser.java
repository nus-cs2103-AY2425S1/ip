package citadel;

import citadel.exception.CitadelException;
import citadel.commands.Commands;
import citadel.exception.CitadelInvalidCommandException;

public class Parser {
    public static Commands parseCommand(String input) throws CitadelException {
        try {
            String command = input.split(" ")[0].toUpperCase();
            return Commands.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CitadelInvalidCommandException();
        }
        }
}
