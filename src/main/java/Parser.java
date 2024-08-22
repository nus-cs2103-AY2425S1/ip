import exception.CitadelException;
import Commands.Commands;
import exception.CitadelInvalidCommandException;

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
