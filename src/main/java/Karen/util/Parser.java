package Karen.util;

import Karen.tasks.Deadline;
import Karen.tasks.Event;
import Karen.tasks.Todo;
import Karen.commands.Command;
import Karen.commands.ListCommand;
import Karen.commands.ExitCommand;
import Karen.commands.DeleteCommand;
import Karen.commands.AddTaskCommand;
import Karen.commands.MarkCommand;
import Karen.commands.UnmarkCommand;
import Karen.commands.UnknownCommand;

import java.time.format.DateTimeParseException;

public class Parser {

    private enum Keywords {
        MARK,
        UNMARK,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        BYE,
        UNKNOWN
    }

    public static Command parse(String input, Ui ui) {
        //TODO implement parameter parsing for each case
        String[] arr = input.split(" ", 2);
        Keywords keyword;
        try {
            keyword = Keywords.valueOf(arr[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            keyword = Keywords.UNKNOWN;
        }
        Command c = null;
        switch (keyword) {
        case BYE:
            c = new ExitCommand();
            break;
        case LIST:
            c = new ListCommand();
            break;
        case TODO:
            try {
                c = new AddTaskCommand(new Todo(arr[1]));
            } catch (IndexOutOfBoundsException e) {
                ui.showTodoSyntax();
            }
            break;
        case DEADLINE:
            try {
                String[] params = arr[1].split(" /by ");
                c = new AddTaskCommand(new Deadline(params[0], params[1]));
            } catch (IndexOutOfBoundsException e) {
                ui.showDeadlineSyntax();
            } catch (DateTimeParseException e) {
                ui.showDateTimeError();
            }
            break;
        case EVENT:
            try {
                String[] params = arr[1].split(" /from ");
                String name = params[0];
                String[] datetimes = params[1].split(" /to ");
                c = new AddTaskCommand(new Event(name, datetimes[0], datetimes[1]));
            } catch (IndexOutOfBoundsException e) {
                ui.showEventSyntax();
            } catch (DateTimeParseException e) {
                ui.showDateTimeError();
            }
            break;
        case MARK:
            try {
                int i = Integer.parseInt(arr[1]) - 1;
                c = new MarkCommand(i);
            } catch (NumberFormatException e) {
                ui.showNotANumberError();
            } catch (IndexOutOfBoundsException e) {
                ui.showMarkSyntax();
            }
            break;
        case UNMARK:
            try {
                int i = Integer.parseInt(arr[1]) - 1;
                c = new UnmarkCommand(i);
            } catch (NumberFormatException e) {
                ui.showNotANumberError();
            } catch (IndexOutOfBoundsException e) {
                ui.showUnmarkSyntax();
            }
            break;
        case DELETE:
            try {
                int i = Integer.parseInt(arr[1]) - 1;
                c = new DeleteCommand(i);
            } catch (NumberFormatException e) {
                ui.showNotANumberError();
            } catch (IndexOutOfBoundsException e) {
                ui.showDeleteSyntax();
            }
            break;
        case UNKNOWN:
            c = new UnknownCommand();
            break;
        }
        return c;
    }
}
