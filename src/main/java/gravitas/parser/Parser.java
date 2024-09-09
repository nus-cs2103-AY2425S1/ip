package gravitas.parser;

import gravitas.command.AddCommand;
import gravitas.command.Command;
import gravitas.command.DeleteCommand;
import gravitas.command.ExitCommand;
import gravitas.command.FindCommand;
import gravitas.command.HelpCommand;
import gravitas.command.ListTaskCommand;
import gravitas.command.UpdateTaskCommand;
import gravitas.exception.DukeException;
import gravitas.task.Deadline;
import gravitas.task.Event;
import gravitas.task.Task;
import gravitas.task.Todo;

/**
 * Parser class is responsible for parsing the user input and executing the respective commands.
 */
public class Parser {

    /**
     * Parses the user input and returns the respective command.
     *
     * @param input User input.
     * @return Command to be executed.
     * @throws DukeException If the input is invalid.
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        String command = inputArray[0];

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListTaskCommand();
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        case "mark", "unmark":
            return new UpdateTaskCommand(input);
        case "todo", "deadline", "event":
            return new AddCommand(input);
        case "help":
            return new HelpCommand();
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the string data and returns the respective task.
     *
     * @param data String data to be parsed.
     * @return Task to be returned.
     * @throws DukeException If there is an error in loading the tasks.
     */
    public static Task parseStringToTask(String data) throws DukeException {
        String[] frags = data.split("\\s*\\|\\s*");
        Task task;
        if (frags[0].equals("T")) {
            task = new Todo(frags[2]);

            if (frags[1].equals("1")) {
                task.markTask();
            }
            return task;
        } else if (frags[0].equals("E")) {
            String startDate = frags[3] + " " + frags[4];
            String endDate = frags[5] + " " + frags[6];
            task = new Event(frags[2], startDate, endDate);

            if (frags[1].equals("1")) {
                task.markTask();
            }
            return task;
        } else if (frags[0].equals("D")) {
            String endDate = frags[3] + " " + frags[4];
            task = new Deadline(frags[2], endDate);

            if (frags[1].equals("1")) {
                task.markTask();
            }
            return task;
        } else {
            throw new DukeException("Error in loading tasks!");
        }
    }
}
