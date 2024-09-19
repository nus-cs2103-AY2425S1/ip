package darkpool.parser;

import darkpool.command.Command;
import darkpool.command.ExitCommand;

/**
 * ExitParser class is responsible for parsing the exit command.
 */
public class ExitParser {

    public static Command parse() {
        return new ExitCommand();
    }

}
