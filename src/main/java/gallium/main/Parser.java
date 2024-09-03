package gallium.main;

import gallium.command.AddCommand;
import gallium.command.ByeCommand;
import gallium.command.Command;
import gallium.command.DateCommand;
import gallium.command.DeleteCommand;
import gallium.command.ListCommand;
import gallium.command.MarkCommand;

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

    public Parser(Ui ui) {
        this.ui = ui;
    }

    public Command parse(String message) {
        while (!message.equals(BYE)) {
            try {
                switch (message) {
                case LIST:
                    return runList();

                case "todo":
                case "todo ":
                case "deadline":
                case "deadline ":
                case "event":
                case "event ":
                    throw new GalliumException("OOPS!!! The description of a " + message + " cannot be empty.");

                default:
                    if (message.matches(MARK + " \\d+") || message.matches(UNMARK + " \\d+")) {
                        return runMark(message);
                    } else if (message.startsWith(TODO) || message.startsWith(DEADLINE)
                            || message.startsWith(EVENT)) {
                        return runAdd(message);
                    } else if (message.startsWith(DELETE)) {
                        return runDelete(message);
                    } else if (message.startsWith(DATE)) {
                        return runDate(message);
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
            message = ui.readNextLine();
        }
        return new ByeCommand();
    }

    public ListCommand runList() {
        return new ListCommand();
    }

    public MarkCommand runMark(String message) {
        return new MarkCommand(message);
    }

    public AddCommand runAdd(String message) {
        return new AddCommand(message);
    }

    public DeleteCommand runDelete(String message) {
        return new DeleteCommand(message);
    }

    public DateCommand runDate(String message) {
        return new DateCommand(message);
    }

}
