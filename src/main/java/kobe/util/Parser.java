package kobe.util;

import kobe.KobeException;
import kobe.command.*;

public class Parser {

    public static Command parse(String fullCommand) throws Exception {
        String[] words = fullCommand.split(" ", 2);
        String commandWord = words[0];

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(words[1]));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(words[1]));
            case "delete":
                return new DeleteCommand(Integer.parseInt(words[1]));
            case "todo":
                return new AddCommand(fullCommand);
            case "deadline":
                return new AddCommand(fullCommand);
            case "event":
                return new AddCommand(fullCommand);
            case "find":
                return new FindCommand(words[1]);
            default:
                throw new KobeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
