package gallium.main;

import gallium.command.AddCommand;
import gallium.command.ByeCommand;
import gallium.command.Command;
import gallium.command.DateCommand;
import gallium.command.DeleteCommand;
import gallium.command.ErrorCommand;
import gallium.command.FindCommand;
import gallium.command.ListCommand;
import gallium.command.MarkCommand;

/**
 * The Parser class interprets user input and generates Command.
 * It handles listing tasks, marking tasks, adding new tasks, deleting tasks,
 * and filtering tasks by dates.
 */
public class Parser {

    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";
    private static final String DATE = "date";
    private static final String FIND = "find";

    private Ui ui;

    /**
     * Constructs a Parser object.
     * 
     * @param ui The UI instance to interact with the user.
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses the input message and returns a Command.
     * The method handles list, mark, unmark, todo, deadline, event, delete, and
     * date. It will continue in the loop to prompt for new input until a "bye"
     * command is received.
     * 
     * @param message The user input message to be parsed.
     * @return The Command to be executed.
     */
    public Command parse(String message) {
        try {
            switch (message) {
            case LIST:
                return new ListCommand();

            case "todo":
            case "todo ":
            case "deadline":
            case "deadline ":
            case "event":
            case "event ":
                throw new GalliumException("OOPS!!! The description of a " + message + " cannot be empty.");

            default:
                if (message.matches(MARK + " \\d+") || message.matches(UNMARK + " \\d+")) {
                    return new MarkCommand(message);
                } else if (message.startsWith(TODO) || message.startsWith(DEADLINE)
                        || message.startsWith(EVENT)) {
                    return new AddCommand(message);
                } else if (message.startsWith(DELETE)) {
                    return new DeleteCommand(message);
                } else if (message.startsWith(DATE)) {
                    return new DateCommand(message);
                } else if (message.startsWith(FIND)) {
                    return new FindCommand(message);
                } else if (message.startsWith(BYE)) {
                    return new ByeCommand();
                } else {
                    throw new GalliumException("OOPS!!! I'm sorry, but I don't know what that means :(");
                }
            }

        } catch (GalliumException e) {
            ui.showGalliumException(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            if (message.startsWith(DEADLINE)) {
                ui.showIncompleteDeadline();
            } else if (message.startsWith(EVENT)) {
                ui.showIncompleteEvent();
            }
        } catch (IndexOutOfBoundsException e) {
            if (message.startsWith(MARK) || message.startsWith(UNMARK) || message.startsWith(DELETE)) {
                ui.showWrongIndex();
            }
        }
        return new ErrorCommand();
    }

}
