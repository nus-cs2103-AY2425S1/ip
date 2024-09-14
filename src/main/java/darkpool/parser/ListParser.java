package darkpool.parser;

import darkpool.command.Command;
import darkpool.command.ListCommand;
import darkpool.DarkpoolException;

public class ListParser {

    static public Command parse() throws DarkpoolException {
        return new ListCommand();
    }

}
