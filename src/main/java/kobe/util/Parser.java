package kobe.util;

import kobe.command.*;
import kobe.KobeException;

/**
 * Parses user input and converts it into a command to be executed by the Kobe chatbot application.
 */
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
            case "deadline":
            case "event":
                return new AddCommand(fullCommand);
            case "find":
                return new FindCommand(words[1]);
            case "tag":
                String[] tagParts = words[1].split(" ", 2);
                if (tagParts.length != 2) {
                    throw new KobeException("Invalid tag command. Use: tag <task number> <#tag>");
                }
                return new TagCommand(Integer.parseInt(tagParts[0]), tagParts[1].replace("#", ""));
            default:
                throw new KobeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
