package kotori.parser;

import kotori.command.*;
import kotori.storage.Storage;
import kotori.taskList.TaskList;
/**
 * This class represents a parser that will
 * parse a String of command line into a command Object
 */

public class Parser {
    private Storage storage;
    private TaskList list;


    /**
     * Create a parser
     * */
    public Parser(Storage storage, TaskList list) {
        this.storage = storage;
        this.list = list;
    }

    /**
     * Parses a string and produce a command Object.
     *
     * @param input the input command line.
     * @return the command going to be execute later
     * */
    public Command parse(String input) {
        input = input.trim();
        @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
        final int lengthOfSearch = 7;
        final int lengthOfFind = 5;
        if (input.equals("bye")) {
            return new ExitCommand(list);
        } else if (input.equals("list")) {
            return new PrintListCommand(list);
        } else if (input.equals("sort")) {
            return new SortCommand(list, storage);
        } else if (input.startsWith("mark ")) {
            return makeMarkCommand(input);
        } else if (input.startsWith("unmark ")) {
            return makeUnarkCommand(input);
        } else if (input.startsWith("delete ")) {
            int index = Integer.parseInt(getInformation(input));
            return new DeleteCommand(storage, list, index);
        } else if (input.startsWith("search ")) {
            return new SearchCommand(list, input.substring(lengthOfSearch));
        } else if (input.startsWith("find ")) {
            return new FindCommand(list, input.substring(lengthOfFind));
        } else {
            return new AddCommand(storage, list, input);
        }
    }
    /**
     * Gets the information of the command line and skip the keyword.
     * */
    private String getInformation(String input) {
        return input.split(" ")[1].trim();
    }
    /**
     * Tries to make a MarkCommand
     * */
    private Command makeMarkCommand(String input) {
        try {
            int index = Integer.parseInt(getInformation(input));
            return new MarkCommand(storage, list, index);
        } catch (NumberFormatException e) {
            return new ErrorCommand("Please enter a valid number for mark command");
        }
    }

    private Command makeUnarkCommand(String input) {
        try {
            int index = Integer.parseInt(getInformation(input));
            return new UnmarkCommand(storage, list, index);
        } catch (NumberFormatException e) {
            return new ErrorCommand("Please enter a valid number for unmark command");
        }
    }
}
