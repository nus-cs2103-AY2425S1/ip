package thebotfather.parser;

import thebotfather.command.Command;
import thebotfather.command.ExitCommand;
import thebotfather.util.TheBotFatherException;

/**
 * The {@code ExitParser} class provides a method to parse the exit command,
 * which signals that the user intends to terminate the program.
 */
public class ExitParser {

    /**
     * Parses the exit command and returns an {@link ExitCommand}.
     *
     * @return an {@code ExitCommand} to terminate the application
     * @throws TheBotFatherException if an error occurs during command processing
     */
    public static Command parse() throws TheBotFatherException {
        return new ExitCommand();
    }
}
