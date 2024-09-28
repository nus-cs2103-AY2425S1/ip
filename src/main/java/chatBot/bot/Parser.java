package chatbot.bot;

import java.time.format.DateTimeParseException;

import chatbot.command.AddCommand;
import chatbot.command.Command;
import chatbot.command.DeleteCommand;
import chatbot.command.ExitCommand;
import chatbot.command.FindCommand;
import chatbot.command.HelpCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkCommand;
import chatbot.command.UnmarkCommand;
import chatbot.exception.EmptyDescException;
import chatbot.exception.InvalidCommandException;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.ToDoTask;

/**
 * Parser is a class to parse input Strings. It has 2 methods
 *
 * parseTask method is used exclusively for parsing tasks from a storage file,
 * returns a Task to the caller.
 *
 * parse method is used to determine the command to go to next.
 * returns a Command to the caller.
 */

public class Parser {
    static Task parseTask(String s) {
        String command = s.split(" ")[0];
        String desc = s.substring(s.indexOf(" "));

        desc = desc.stripLeading();

        Task t = null;

        if (command.equals("todo")) {
            try {
                t = new ToDoTask(desc);
            } catch (Exception e) {
                Ui.printAnything(e.getMessage());
            }
        } else if (command.equals("deadline")) {
            try {
                String[] arr = desc.split("/by");
                try {
                    t = new Deadline(arr[0].strip(), arr[1].strip());
                } catch (ArrayIndexOutOfBoundsException e) {
                    Ui.printAnything("missing /by");
                } catch (DateTimeParseException ex) {
                    Ui.printAnything("Incorrect date format stored: " + arr[1].strip());
                }
            } catch (EmptyDescException e) {
                Ui.printAnything(e.getMessage());
            }
        } else if (command.equals("event")) {
            String[] arr = desc.split("/from");
            String[] arr2 = new String[0];
            try {
                arr2 = arr[1].split("/to");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("missing /from");
            }
            t = null;
            try {
                try {
                    t = new Event(arr[0].strip(), arr2[0].strip(), arr2[1].strip());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("missing /to");
                } catch (DateTimeParseException ex) {
                    System.out.println("Incorrect date format stored: " + arr[1].strip());
                }
            } catch (Exception e) {
                Ui.printAnything(e.getMessage());
            }
        } else {
            try {
                throw new InvalidCommandException();
            } catch (InvalidCommandException e) {
                Ui.printAnything(e.getMessage());
            }
            //System.out.println("Unknown command: " + command);
        }

        if (t == null) {
            System.out.println("null tasked returned back to Storage.load()");
        }

        return t;
    }

    static Command parse(String fullCommand) {
        String action = fullCommand.split(" ")[0];
        String desc = fullCommand.substring(fullCommand.indexOf(" ") + 1);
        if (!fullCommand.contains(" ")) {
            desc = "";
        }
        Command c = null;
        if (action.equals("mark")) {
            c = new MarkCommand(Integer.parseInt(desc) - 1);
        } else if (action.equals("unmark")) {
            c = new UnmarkCommand(Integer.parseInt(desc) - 1);
        } else if (action.equals("list")) {
            c = new ListCommand();
        } else if (action.equals("todo") || action.equals("deadline") || action.equals("event")) {
            c = new AddCommand(action, desc);
        } else if (action.equals("delete")) {
            c = new DeleteCommand(Integer.parseInt(desc));
            // this.deleteTask(Integer.parseInt(desc));
        } else if (action.equals("bye")) {
            c = new ExitCommand();
        } else if (action.equals("find")) {
            c = new FindCommand(desc);
        } else if (action.equals("help")) {
            c = new HelpCommand(desc);
        } else {
            try {
                throw new InvalidCommandException();
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
            //System.out.println("Unknown command: " + command);
        }
        return c;
    }
}
