package johncena.parser;

import johncena.commands.HelloCommand;
import johncena.commands.ByeCommand;
import johncena.commands.EventCommand;
import johncena.commands.TodoCommand;
import johncena.commands.DeadlineCommand;
import johncena.commands.ListCommand;
import johncena.commands.MarkCommand;
import johncena.commands.UnmarkCommand;
import johncena.commands.DeleteCommand;
import johncena.commands.OnCommand;
import johncena.commands.HelpCommand;
import johncena.commands.Command;
import johncena.commands.FindCommand;

import johncena.exceptions.CenaException;
import johncena.exceptions.CenaInvalidTaskIndexException;
import johncena.exceptions.CenaInvalidDeadlineException;
import johncena.exceptions.CenaInvalidEventException;
import johncena.exceptions.CenaUnknownCommandException;

import johncena.tasks.Task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The {@code InputHandler} class is responsible for handling user input and executing the corresponding commands.
 */
public class InputHandler {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new {@code InputHandler} with the specified list of tasks.
     *
     * @param tasks the list of tasks
     */
    public InputHandler(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Handles the user input and executes the corresponding command.
     *
     * @param input the user input
     * @throws CenaException if an exception occurs while executing the command
     */
    public void handleInput(String input) throws CenaException {
        Command command = null;

        try {
            if (input.equals("bye")) {
                command = new ByeCommand();
            } else if (input.equals("list")) {
                command = new ListCommand(tasks);
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                command = new MarkCommand(tasks, taskIndex);
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                command = new UnmarkCommand(tasks, taskIndex);
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5).trim();
                command = new TodoCommand(tasks, description);
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                if (parts.length < 2) {
                    throw new CenaInvalidDeadlineException("Incorrect description for deadline. It should contain only /by.");
                }
                command = new DeadlineCommand(tasks, parts[0], parts[1]);
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from | /to ");
                if (parts.length < 3) {
                    throw new CenaInvalidEventException("Incorrect description for event. It should contain /from and /to.");
                }
                command = new EventCommand(tasks, parts[0], parts[1], parts[2]);
            } else if (input.startsWith("delete ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                command = new DeleteCommand(tasks, taskIndex);
            } else if (input.equals("hello") || input.equals("hi") || input.equals("hey") || input.equals("yo") || input.equals("sup")) {
                command = new HelloCommand();
            } else if (input.equals("help") || input.equals("commands") || input.equals("command") || input.equals("cmds") || input.equals("cmd")) {
                command = new HelpCommand();
            } else if (input.startsWith("on ")) {
                String date = input.substring(3).trim();
                command = new OnCommand(tasks, date);
            } else if (input.startsWith("find ")) {
                String keyword = input.substring(5).trim();
                command = new FindCommand(tasks, keyword);
            } else {
                throw new CenaUnknownCommandException("I'm sorry, but I don't know what that means :-(");
            }

            if (command != null) {
                command.execute();
            }

        } catch (CenaInvalidTaskIndexException | CenaInvalidDeadlineException | CenaInvalidEventException | DateTimeParseException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" OOPS!!! " + e.getMessage());
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" OOPS!!! Task number must be a valid integer.");
            System.out.println("____________________________________________________________");
        }
    }
}
