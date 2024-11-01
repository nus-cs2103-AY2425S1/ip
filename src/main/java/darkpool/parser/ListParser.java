package darkpool.parser;

import darkpool.command.Command;
import darkpool.command.ListCommand;

/**
 * ListParser class is responsible for parsing the list command.
 */
public class ListParser {

    public static Command parse() {
        return new ListCommand();
    }

}
