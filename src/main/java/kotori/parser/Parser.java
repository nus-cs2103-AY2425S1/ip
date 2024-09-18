package kotori.parser;

import kotori.command.AddCommand;
import kotori.command.Command;
import kotori.command.DeleteCommand;
import kotori.command.ErrorCommand;
import kotori.command.ExitCommand;
import kotori.command.FindCommand;
import kotori.command.MarkCommand;
import kotori.command.PrintListCommand;
import kotori.command.SearchCommand;
import kotori.command.SortCommand;
import kotori.command.UnmarkCommand;
import kotori.storage.Storage;
import kotori.tasklist.TaskList;
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
            return makeDeleteCommand(input);
        } else if (input.startsWith("search ")) {
            return new SearchCommand(list, input.substring(lengthOfSearch));
        } else if (input.startsWith("find ")) {
            return new FindCommand(list, input.substring(lengthOfFind));
        } else if (input.startsWith("todo ")
                || input.startsWith("deadline ")
                || input.startsWith("event ")) {
            return new AddCommand(storage, list, input);
        } else {
            return new ErrorCommand("Sorry, I can not understand this command Q_Q\nPlease enter a valid command");
        }
    }
    /**
     * Gets the information of the command line and skip the keyword.
     * */
    private String getInformation(String input) throws InvalidNumberOfArgumentException {
        if (input.split(" ").length > 2) {
            throw new InvalidNumberOfArgumentException("There are too many argument for me to handle.... Sorry");
        }
        return input.split(" ")[1].trim();
    }
    /**
     * Tries to make a MarkCommand
     * @param input the input String.
     * @return the command made
     * */
    private Command makeMarkCommand(String input) {
        try {
            int index = Integer.parseInt(getInformation(input));
            return new MarkCommand(storage, list, index);
        } catch (NumberFormatException e) {
            return new ErrorCommand("Please enter a valid number for mark command");
        } catch (InvalidNumberOfArgumentException e) {
            return new ErrorCommand(e.getMessage());
        }
    }
    /**
     * Tries to make a Unmark Command
     * @param input the input String.
     * @return  the command made.
     * */
    private Command makeUnarkCommand(String input) {
        try {
            int index = Integer.parseInt(getInformation(input));
            return new UnmarkCommand(storage, list, index);
        } catch (NumberFormatException e) {
            return new ErrorCommand("Please enter a valid number for unmark command");
        } catch (InvalidNumberOfArgumentException e) {
            return new ErrorCommand(e.getMessage());
        }
    }
    /**
     * Tries to make a delete Command
     * @param input the input String.
     * @return  the command made.
     * */
    private Command makeDeleteCommand(String input) {
        try {
            int index = Integer.parseInt(getInformation(input));
            return new DeleteCommand(storage, list, index);
        } catch (NumberFormatException e) {
            return new ErrorCommand("Please enter a valid number for unmark command");
        } catch (InvalidNumberOfArgumentException e) {
            return new ErrorCommand(e.getMessage());
        }
    }
}
