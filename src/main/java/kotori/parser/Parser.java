package kotori.parser;

import kotori.command.AddCommand;
import kotori.command.Command;
import kotori.command.DeleteCommand;
import kotori.command.ExitCommand;
import kotori.command.FindCommand;
import kotori.command.MarkCommand;
import kotori.command.PrintListCommand;
import kotori.command.SearchCommand;
import kotori.command.UnmarkCommand;
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
        } else if (input.startsWith("mark ")) {
            int index = Integer.parseInt(getInformation(input));
            return new MarkCommand(storage, list, index);
        } else if (input.startsWith("unmark ")) {
            int index = Integer.parseInt(getInformation(input));
            return new UnmarkCommand(storage, list, index);
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
     * Get the information of the command line and skip the keyword.
     * */
    private String getInformation(String input) {
        return input.split(" ")[1].trim();
    }
}
