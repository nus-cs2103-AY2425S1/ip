package karen.util;

import java.time.format.DateTimeParseException;

import karen.commands.AddTaskCommand;
import karen.commands.Command;
import karen.commands.DeleteCommand;
import karen.commands.ExitCommand;
import karen.commands.FindCommand;
import karen.commands.ListCommand;
import karen.commands.MarkCommand;
import karen.commands.ShowErrorCommand;
import karen.commands.SortCommand;
import karen.commands.SortCommand.Criteria;
import karen.commands.SortCommand.Order;
import karen.commands.UnknownCommand;
import karen.commands.UnmarkCommand;
import karen.tasks.Deadline;
import karen.tasks.Event;
import karen.tasks.Todo;

/**
 * Handles all logic relevant to string processing of the user input, such as extracting keywords and parameters
 */
public class Parser {

    private enum Keywords {
        MARK,
        UNMARK,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        SORT,
        BYE,
        UNKNOWN
    }

    /**
     * Returns a {@code Command} after parsing the user's input
     * @param input A String representing the user input to System.in
     * @param ui Ui object which handles all input/output processes
     * @return {@code Command}
     */
    public static Command parse(String input, Ui ui) {
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
                c = new ShowErrorCommand(ui.showTodoSyntax());
            }
            break;
        case DEADLINE:
            try {
                String[] params = arr[1].split(" /by ");
                c = new AddTaskCommand(new Deadline(params[0], params[1]));
            } catch (IndexOutOfBoundsException e) {
                c = new ShowErrorCommand(ui.showDeadlineSyntax());
            } catch (DateTimeParseException e) {
                c = new ShowErrorCommand(ui.showDateTimeError());
            }
            break;
        case EVENT:
            try {
                String[] params = arr[1].split(" /from ");
                String name = params[0];
                String[] dateTimes = params[1].split(" /to ");
                c = new AddTaskCommand(new Event(name, dateTimes[0], dateTimes[1]));
            } catch (IndexOutOfBoundsException e) {
                c = new ShowErrorCommand(ui.showEventSyntax());
            } catch (DateTimeParseException e) {
                c = new ShowErrorCommand(ui.showDateTimeError());
            }
            break;
        case MARK:
            try {
                int i = Integer.parseInt(arr[1]) - 1;
                c = new MarkCommand(i);
            } catch (NumberFormatException e) {
                c = new ShowErrorCommand(ui.showNotANumberError());
            } catch (IndexOutOfBoundsException e) {
                c = new ShowErrorCommand(ui.showMarkSyntax());
            }
            break;
        case UNMARK:
            try {
                int i = Integer.parseInt(arr[1]) - 1;
                c = new UnmarkCommand(i);
            } catch (NumberFormatException e) {
                c = new ShowErrorCommand(ui.showNotANumberError());
            } catch (IndexOutOfBoundsException e) {
                c = new ShowErrorCommand(ui.showUnmarkSyntax());
            }
            break;
        case DELETE:
            try {
                int i = Integer.parseInt(arr[1]) - 1;
                c = new DeleteCommand(i);
            } catch (NumberFormatException e) {
                c = new ShowErrorCommand(ui.showNotANumberError());
            } catch (IndexOutOfBoundsException e) {
                c = new ShowErrorCommand(ui.showDeleteSyntax());
            }
            break;
        case FIND:
            try {
                String searchWord = arr[1];
                c = new FindCommand(searchWord);
            } catch (IndexOutOfBoundsException e) {
                c = new ShowErrorCommand(ui.showFindSyntax());
            }
            break;
        case SORT:
            // sort date /order ascending
            try {
                String[] sortParams = arr[1].split(" /order ");
                Criteria criteria = Criteria.valueOf(sortParams[0].toUpperCase());
                Order order = Order.valueOf(sortParams[1].toUpperCase());
                c = new SortCommand(criteria, order);
            } catch (IndexOutOfBoundsException e) {
                c = new ShowErrorCommand(ui.showSortSyntax());
            } catch (IllegalArgumentException e) {
                c = new ShowErrorCommand(ui.showSortKeys());
            }
            break;
        case UNKNOWN:
            c = new UnknownCommand();
            break;
        default:
            c = new UnknownCommand();
            break;
        }
        return c;
    }
}
