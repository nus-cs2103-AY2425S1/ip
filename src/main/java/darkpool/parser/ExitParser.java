package darkpool.parser;

import darkpool.command.Command;
import darkpool.command.ExitCommand;
import darkpool.util.DarkpoolException;

public class ExitParser {

    static public Command parse() throws DarkpoolException {
        return new ExitCommand();
    }

}
