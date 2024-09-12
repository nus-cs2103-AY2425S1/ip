package waterfall;

import waterfall.command.AddCommand;
import waterfall.command.Command;
import waterfall.command.DeleteCommand;
import waterfall.command.ExitCommand;
import waterfall.command.MarkCommand;
import waterfall.command.SearchCommand;
import waterfall.command.ShowTasksCommand;
import waterfall.command.UndoCommand;
import waterfall.command.UnmarkCommand;
import waterfall.command.UnrecognisedCommand;

/**
 * The <code>Parser</code> class is responsible for interpreting user input and converting it
 * into corresponding commands to be executed by the application.
 * It analyzes the user input string and determines which specific command class
 * should be instantiated based on the input format.
 *
 * @author Wai Hong
 */

public class Parser {

    /**
     * Parses the user input and returns the appropriate <code>Command</code> object.
     * <p>
     * The method determines the type of command based on the input string and extracts
     * necessary parameters for the command (like task title, dates, etc.).
     * It handles different command types such as "bye", "list", "mark", "unmark",
     * "delete", "deadline", "todo" , and "event".
     * </p>
     *
     * @param input The user input string to be parsed.
     * @return The corresponding <code>Command</code> object based on the user input.
     * @throws WaterfallException if the input format is invalid or if required fields are missing.
     */
    public static Command parse(String input) throws WaterfallException {
        assert input != null : "input should be non null";
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ShowTasksCommand();
        } else if (input.equals("undo")) {
            return new UndoCommand();
        } else if (input.startsWith("mark ") && (input.substring(5).matches("\\d+"))) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            return new MarkCommand(index);
        } else if (input.startsWith("unmark ") && (input.substring(7).matches("\\d+"))) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            return new UnmarkCommand(index);
        } else if (input.startsWith("delete ") && (input.substring(7).matches("\\d+"))) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            return new DeleteCommand(index);
        } else if (input.startsWith("todo ")) {
            String title = input.substring(5);
            if (title.isEmpty()) {
                throw new WaterfallException("Bruh what is this empty title are you kidding me!");
            }
            return new AddCommand(title);
        } else if (input.startsWith("deadline ")) {
            if (!input.contains("/")) {
                throw new WaterfallException("oh man where's your deadline!");
            }
            int index = input.indexOf("/");
            if (index <= 9) {
                throw new WaterfallException("Bruh what is this empty title are you kidding me!");
            }
            String title = input.substring(9, index - 1);
            if (title.isEmpty()) {
                throw new WaterfallException("Bruh what is this empty title are you kidding me!");
            }
            String description = input.substring(index + 1);
            if (!description.startsWith("by ")) {
                throw new WaterfallException("unrecognised comment: " + description);
            }
            return new AddCommand(title, description.substring(3));
        } else if (input.startsWith("event ")) {
            String[] inputs = input.split(" /");
            if (inputs.length != 3) {
                throw new WaterfallException("invalid event format: An event must contain only from and to comments");
            }
            if (inputs[0].length() <= 5) {
                throw new WaterfallException("Bruh what is this empty title are you kidding me!");
            }
            String title = inputs[0].substring(6);
            if (title.isEmpty()) {
                throw new WaterfallException("Bruh what is this empty title are you kidding me!");
            }
            String from;
            String to;
            if (inputs[1].startsWith("from ")) {
                from = inputs[1].substring(5);
                if (inputs[2].startsWith("to ")) {
                    to = inputs[2].substring(3);
                } else {
                    throw new WaterfallException("invalid format: missing to comment");
                }
            } else if (inputs[1].startsWith("to")) {
                to = inputs[1].substring(3);
                if (inputs[2].startsWith("from ")) {
                    from = inputs[2].substring(5);
                } else {
                    throw new WaterfallException("invalid format: missing from comment");
                }
            } else {
                throw new WaterfallException("invalid format: missing to and from comment");
            }
            if (to.isEmpty()) {
                throw new WaterfallException("Bruh what is this empty to command are you kidding me!");
            }
            if (from.isEmpty()) {
                throw new WaterfallException("Bruh what is this empty from command are you kidding me!");
            }
            return new AddCommand(title, from, to);
        } else if (input.startsWith("find ")) {
            if (input.length() <= 5) {
                throw new WaterfallException("Bruh what is this empty task you are looking for!");
            }
            String targetTitle = input.substring(5);
            return new SearchCommand(targetTitle);
        } else {
            return new UnrecognisedCommand();
        }
    }
}
