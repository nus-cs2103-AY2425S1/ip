package rotodo.processes;

import rotodo.commands.AddCommand;
import rotodo.commands.Command;
import rotodo.commands.DeleteCommand;
import rotodo.commands.ExitCommand;
import rotodo.commands.FindCommand;
import rotodo.commands.HelpCommand;
import rotodo.commands.ListCommand;
import rotodo.commands.MarkCommand;
import rotodo.exception.IncompleteInputException;
import rotodo.exception.InvalidInputException;

/**
 * This class parses user inputs and outputs a Command
 * that can be executed.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class Parser {
    /**
     * Different types of tasks
     */
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";

    /**
     * Different types of commands
     */
    private static final String HELP = "help";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";
    private static final String LIST = "list";
    private static final String FIND = "find";
    private static final String EXIT = "bye";

    /**
     * Parses user input, then outputs a Command that can be executed.
     *
     * @param input user input
     * @return Command based on user input
     * @throws InvalidInputException
     */
    public static Command process(String input) throws InvalidInputException {
        String[] token = input.split(" ", 2);
        boolean asDone = false;
        switch (token[0]) {
        case LIST:
            return new ListCommand();

        case EXIT:
            return new ExitCommand();

        case FIND:
            return makeFind(token);

        case DELETE:
            return makeDelete(token);

        case MARK:
            asDone = true;
            // Fall through

        case UNMARK:
            return makeMark(token, asDone);

        case TODO:
            return makeAddTodo(token);

        case DEADLINE:
            return makeAddDeadline(token);

        case EVENT:
            return makeAddEvent(token);

        case HELP:
            return new HelpCommand();

        default:
            throw new InvalidInputException(
                    "Reep Roop... RoTodo Read No Understand?");
        }
    }

    private static FindCommand makeFind(String[] arg) throws InvalidInputException {
        if (arg.length < 2) {
            throw new IncompleteInputException(
                "RoTodo need keyword to search for");
        }
        return new FindCommand(arg[1]);
    }

    private static DeleteCommand makeDelete(String[] arg) throws InvalidInputException {
        if (arg.length < 2) {
            throw new IncompleteInputException(
                "RoTodo need task number");
        }
        try {
            int index = Integer.parseInt(arg[1].split(" ")[0]);
            return new DeleteCommand(index - 1);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format("'%s' not a "
                    + "number RoTodo knows (and RoTodo know much numbers, "
                    + "like 1s and 0s)", arg[1]));
        }
    }

    private static MarkCommand makeMark(String[] arg, boolean asDone) throws InvalidInputException {
        if (arg.length < 2) {
            throw new IncompleteInputException(
                    "RoTodo need task number");
        }
        try {
            int index = Integer.parseInt(arg[1].split(" ")[0]);
            return new MarkCommand(index - 1, asDone);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format("'%s' not a "
                    + "number RoTodo knows (and RoTodo know much numbers, "
                    + "like 1s and 0s)", arg[1]));
        }
    }

    private static AddCommand makeAddTodo(String[] arg) throws InvalidInputException {
        if (arg.length < 2) {
            throw new IncompleteInputException(
                    "RoTodo can't read your mind, otherwise "
                    + "RoTodo's creator would be rich!\n"
                    + "  RoTodo needs a task description");
        }
        return new AddCommand(AddCommand.TaskType.TODO, arg[1]);
    }

    private static AddCommand makeAddDeadline(String[] arg) throws InvalidInputException {
        if (arg.length < 2) {
            throw new IncompleteInputException(
                    "RoTodo can't read your mind, otherwise "
                    + "RoTodo's creator would be rich!\n"
                    + "  RoTodo needs a task description and deadline");
        }
        arg = arg[1].split(" /by ", 2);
        if (arg.length < 2) {
            throw new IncompleteInputException(
                    "RoTodo can't read your mind, otherwise "
                    + "RoTodo's creator would be rich!\n"
                    + "  RoTodo needs a task description and deadline");
        }
        return new AddCommand(AddCommand.TaskType.DEADLINE, arg[0], arg[1]);
    }

    private static AddCommand makeAddEvent(String[] arg) throws InvalidInputException {
        if (arg.length < 2) {
            throw new IncompleteInputException(
                    "RoTodo can't read your mind, otherwise "
                    + "RoTodo's creator would be rich!\n"
                    + "  RoTodo needs a task description, from and to date/time");
        }
        arg = arg[1].split(" /from | /to ", 3);
        if (arg.length < 3) {
            throw new IncompleteInputException(
                    "RoTodo can't read your mind, otherwise "
                    + "RoTodo's creator would be rich!\n"
                    + "  RoTodo needs a task description, from and to date/time");
        }
        return new AddCommand(AddCommand.TaskType.EVENT, arg[0], arg[1], arg[2]);
    }
}
