package gallium.main;

import gallium.command.AddCommand;
import gallium.command.ByeCommand;
import gallium.command.Command;
import gallium.command.DateCommand;
import gallium.command.DeleteCommand;
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
     * @param Message The user input message to be parsed.
     * @return The Command to be executed.
     */
    public Command parse(String Message) {
        while (!Message.equals(BYE)) {
            try {
                switch (Message) {
                    case LIST:
                        return new ListCommand();

                    case "todo":
                    case "todo ":
                    case "deadline":
                    case "deadline ":
                    case "event":
                    case "event ":
                        throw new GalliumException("OOPS!!! The description of a " + Message + " cannot be empty.");

                    default:
                        if (Message.matches(MARK + " \\d+") || Message.matches(UNMARK + " \\d+")) {
                            return new MarkCommand(Message);
                        } else if (Message.startsWith(TODO) || Message.startsWith(DEADLINE)
                                || Message.startsWith(EVENT)) {
                            return new AddCommand(Message);
                        } else if (Message.startsWith(DELETE)) {
                            return new DeleteCommand(Message);
                        } else if (Message.startsWith(DATE)) {
                            return new DateCommand(Message);
                        } else {
                            throw new GalliumException("OOPS!!! I'm sorry, but I don't know what that means :(");
                        }
                }

            } catch (GalliumException e) {
                ui.showGalliumException(e);
            } catch (ArrayIndexOutOfBoundsException e) {
                if (Message.startsWith(DEADLINE)) {
                    ui.showIncompleteDeadline();
                } else if (Message.startsWith(EVENT)) {
                    ui.showIncompleteEvent();
                }
            } catch (IndexOutOfBoundsException e) {
                if (Message.startsWith(MARK) || Message.startsWith(UNMARK) || Message.startsWith(DELETE)) {
                    ui.showWrongIndex();
                }
            }
            Message = ui.readNextLine();
        }
        return new ByeCommand();
    }

}
