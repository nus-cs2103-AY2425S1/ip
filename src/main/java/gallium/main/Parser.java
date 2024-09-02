package gallium.main;

import gallium.command.AddCommand;
import gallium.command.ByeCommand;
import gallium.command.Command;
import gallium.command.DateCommand;
import gallium.command.DeleteCommand;
import gallium.command.FindCommand;
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
    private static final String FIND = "find";

    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    public Command parse(String Message) {
        while (!Message.equals(BYE)) {
            try {
                switch (Message) {
                    case LIST:
                        return runList();

                    case "todo":
                    case "todo ":
                    case "deadline":
                    case "deadline ":
                    case "event":
                    case "event ":
                        throw new GalliumException("OOPS!!! The description of a " + Message + " cannot be empty.");

                    default:
                        if (Message.matches(MARK + " \\d+") || Message.matches(UNMARK + " \\d+")) {
                            return runMark(Message);
                        } else if (Message.startsWith(TODO) || Message.startsWith(DEADLINE)
                                || Message.startsWith(EVENT)) {
                            return runAdd(Message);
                        } else if (Message.startsWith(DELETE)) {
                            return runDelete(Message);
                        } else if (Message.startsWith(DATE)) {
                            return runDate(Message);
                        } else if (Message.startsWith(FIND)) {
                            return runFind(Message);
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

    public ListCommand runList() {
        return new ListCommand();
    }

    public MarkCommand runMark(String Message) {
        return new MarkCommand(Message);
    }

    public AddCommand runAdd(String Message) {
        return new AddCommand(Message);
    }

    public DeleteCommand runDelete(String Message) {
        return new DeleteCommand(Message);
    }

    public DateCommand runDate(String Message) {
        return new DateCommand(Message);
    }

    public FindCommand runFind(String Message) {
        return new FindCommand(Message);
    }

}
