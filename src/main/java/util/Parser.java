package main.java.util;

import main.java.Deadline;
import main.java.Event;
import main.java.Todo;
import main.java.commands.*;

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

    public static Command parse(String input) {
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
                c = new TodoCommand(new Todo(arr[1]));
            } catch (IndexOutOfBoundsException e) {
                //Make Ui display missing todo name error
                System.out.println("Missing todo name");
            }
            break;
        case DEADLINE:
            try {
                String[] params = arr[1].split(" /by ");
                c = new DeadlineCommand(new Deadline(params[0], params[1]));
            } catch (IndexOutOfBoundsException e) {
                //Make Ui display invalid Deadline syntax message
                System.out.println("Invalid deadline syntax");
            }
            break;
        case EVENT:
            try {
                String[] params = arr[1].split(" /from ");
                String name = params[0];
                String[] datetimes = params[1].split(" /to ");
                c = new EventCommand(new Event(name, datetimes[0], datetimes[1]));
            } catch (IndexOutOfBoundsException e) {
                //Make Ui display invalid Event syntax message
                System.out.println("Invalid Event syntax");
            }
            break;
        case MARK:
            try {
                int i = Integer.parseInt(arr[1]);
                c = new MarkCommand(i);
            } catch (NumberFormatException e) {
                //arr[1] is not a number
                System.out.println("Please enter a valid number");
            } catch (IndexOutOfBoundsException e) {
                //arr[1] doesn't exist
                System.out.println("Invalid mark syntax");
            }
            break;
        case UNMARK:
            try {
                int i = Integer.parseInt(arr[1]);
                c = new UnmarkCommand(i);
            } catch (NumberFormatException e) {
                //arr[1] is not a number
                System.out.println("Please enter a valid number");
            } catch (IndexOutOfBoundsException e) {
                //arr[1] doesn't exist
                System.out.println("Invalid unmark syntax");
            }
            break;
        case DELETE:
            try {
                int i = Integer.parseInt(arr[1]);
                c = new DeleteCommand(i);
            } catch (NumberFormatException e) {
                //arr[1] is not a number
                System.out.println("Please enter a valid number");
            } catch (IndexOutOfBoundsException e) {
                //arr[1] doesn't exist
                System.out.println("Invalid delete syntax");
            }
            break;
        case UNKNOWN:
            c = new UnknownCommand();
            break;
        }
        return c;
    }
}
