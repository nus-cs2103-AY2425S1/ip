package chatbot.bot;

import chatbot.command.AddCommand;
import chatbot.command.Command;
import chatbot.command.DeleteCommand;
import chatbot.command.ExitCommand;
import chatbot.command.FindCommand;
import chatbot.command.HelpCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkCommand;
import chatbot.command.UnmarkCommand;
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

        try {
            if (command.equals("todo")) {
                t = new ToDoTask(desc);
            } else if (command.equals("deadline")) {
                String[] arr = desc.split("/by");
                t = new Deadline(arr[0].strip(), arr[1].strip());
            } else if (command.equals("event")) {
                String[] arr = desc.split("/from");
                String[] arr2 = new String[0];
                arr2 = arr[1].split("/to");
                t = new Event(arr[0].strip(), arr2[0].strip(), arr2[1].strip());
            }
        } catch (Exception e) {
            System.out.println("Error occurred when loading tasks: " + e.getMessage());
        }

        if (t == null) {
            System.out.println("null tasked returned back to Storage.load()");
        }

        return t;
    }

    static Command parse(String fullCommand) throws Exception {
        fullCommand = fullCommand.stripLeading();
        String action = fullCommand.split(" ")[0];
        String desc = fullCommand.substring(fullCommand.indexOf(" ") + 1);
        if (!fullCommand.contains(" ")) {
            desc = "";
        }
        Command c = null;
        if (action.equals("mark")) {
            c = new MarkCommand(Integer.parseInt(desc.strip()) - 1);
        } else if (action.equals("unmark")) {
            c = new UnmarkCommand(Integer.parseInt(desc.strip()) - 1);
        } else if (action.equals("list")) {
            c = new ListCommand();
        } else if (action.equals("todo") || action.equals("deadline") || action.equals("event")) {
            c = new AddCommand(action, desc);
        } else if (action.equals("delete")) {
            c = new DeleteCommand(Integer.parseInt(desc.strip()) - 1);
            // this.deleteTask(Integer.parseInt(desc));
        } else if (action.equals("bye")) {
            c = new ExitCommand();
        } else if (action.equals("find")) {
            c = new FindCommand(desc.strip());
        } else if (action.equals("help")) {
            c = new HelpCommand(desc.strip());
        } else {
            throw new InvalidCommandException();
            //System.out.println("Unknown command: " + command);
        }

        if (desc.isBlank() && !action.equals("list") && !action.equals("bye") && !action.equals("help")) {
            throw new Exception("You must enter some value after the command");
        }
        return c;
    }
}
