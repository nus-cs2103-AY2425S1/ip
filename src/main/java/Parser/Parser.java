package Parser;

import Command.Command;
import Command.AddCommand;
import Command.PrintListCommand;
import Command.ExitCommand;
import Command.InvalidCommand;
import Command.MarkCommand;
import Command.UnmarkCommand;
import Command.DeleteCommand;
import Command.FindCommand;

import Storage.Storage;

import Task.InvalidTaskException;
import Task.RasputinException;
import Task.TaskList;

/**
 * Parses the input from the user to be passed into the commands.
 */
public class Parser {

    private TaskList tasks;
    private Storage storage;


    public Parser(TaskList tasks, Storage storage) {
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Parses the input from the user to determine whether it is a valid input.
     * If input is valid, returns the command to be executed.
     * Else, returns an InvalidCommand.
     *
     * @param input Input text from the user.
     * @return Command to be executed.
     */
    public Command parseInput(String input) {
        String command = input.split(" ")[0];
        switch (command) {
            case "list":
                return new PrintListCommand(tasks);
            case "bye":
                return new ExitCommand(storage, tasks);
            case "mark":
                if (tasks.isEmpty()) {
                    return new InvalidCommand("No tasks in list!");
                }
                int index;
                try {
                    index = Character.getNumericValue(input.charAt(5)) - 1;
                    return new MarkCommand(tasks, index);
                } catch (StringIndexOutOfBoundsException e) {
                    return new InvalidCommand("ERROR! Task to be marked not specified.");
                } catch (IndexOutOfBoundsException e) {
                    return new InvalidCommand("ERROR! Task not found.");
                }
            case "unmark":
                if (tasks.isEmpty()) {
                    return new InvalidCommand("No tasks in list!");
                }

                try {
                    index = Character.getNumericValue(input.charAt(7)) - 1;
                    return new UnmarkCommand(tasks, index);
                } catch (StringIndexOutOfBoundsException e) {
                    return new InvalidCommand("ERROR! Task to be unmarked not specified.");
                } catch (IndexOutOfBoundsException e) {
                    return new InvalidCommand("ERROR! Task not found.");
                }
            case "delete":
                try {
                    index = Character.getNumericValue(input.charAt(7)) - 1;
                    return new DeleteCommand(tasks, index);
                } catch (StringIndexOutOfBoundsException e) {
                    return new InvalidCommand("ERROR! Task to be deleted not specified.");
                } catch (IndexOutOfBoundsException e) {
                    return new InvalidCommand("ERROR! Task not found.");
                }
            case "todo":
            case "deadline":
            case "event":
                try {
                    return new AddCommand(tasks, input);
                } catch (InvalidTaskException e) {
                    return new InvalidCommand(e.getMessage());
                }
            case "find":
                try {
                    return new FindCommand(input, tasks);
                } catch (RasputinException e) {
                    return new InvalidCommand(e.getMessage());
                }



        }
        return new InvalidCommand("ERROR! Unknown command.");
    }

}
