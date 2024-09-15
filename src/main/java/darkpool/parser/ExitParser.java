package darkpool.parser;

import darkpool.command.Command;
import darkpool.command.ExitCommand;
import darkpool.DarkpoolException;

public class ExitParser {

    public static Command parse() throws DarkpoolException {
        return new ExitCommand();
    }

}
