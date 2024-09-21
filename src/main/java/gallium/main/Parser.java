package gallium.main;

import gallium.command.AddCommand;
import gallium.command.ByeCommand;
import gallium.command.Command;
import gallium.command.DateCommand;
import gallium.command.DeleteCommand;
import gallium.command.ErrorCommand;
import gallium.command.EditCommand;
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
    private static final String EDIT = "edit";
    private static final String HI = "hi";
    private static final String HELLO = "hello";

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
        assert message != null && !message.isEmpty(): "Message cannot be empty";
        message = message.trim();
        try {
            switch (message) {
            case LIST:
                return new ListCommand();
            case TODO:
            case DEADLINE:
            case EVENT:
                throw new GalliumException("Oh nooo! The description of a " + message + " cannot be empty.");
            default:
            return returnCommand(message);
            }

        } catch (GalliumException e) {
            ui.showGalliumException(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            handleArrayIndexOutOfBounds(message);
        } catch (IndexOutOfBoundsException e) {
            handleIndexOutOfBounds(message);
        }
        return new ErrorCommand();
    }

    private Command returnCommand(String message) throws GalliumException {
        assert message != null && message != "": "Message cannot be empty";
        if (message.startsWith(MARK) || message.startsWith(UNMARK)) {
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
        } else if (message.startsWith(EDIT)) {
            return new EditCommand(message);
        } else if (message.startsWith(HI) || message.startsWith(HELLO)) {
            throw new GalliumException("Hii!! :>");
        } else {
            throw new GalliumException("Ohh noooo! I'm sorry, but I don't know what that means :(");
        }
    }

    private void handleArrayIndexOutOfBounds(String message) {
        if (message.startsWith(DEADLINE)) {
            ui.showIncompleteDeadline();
        } else if (message.startsWith(EVENT)) {
            ui.showIncompleteEvent();
        }
    }

    private void handleIndexOutOfBounds(String message) {
        if (message.startsWith(MARK) || message.startsWith(UNMARK) || message.startsWith(DELETE)) {
            ui.showWrongIndex();
        }
    }

}
