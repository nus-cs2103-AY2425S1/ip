package rotodo.processes;

import rotodo.commands.Command;
import rotodo.commands.AddCommand;
import rotodo.commands.DeleteCommand;
import rotodo.commands.ExitCommand;
import rotodo.commands.HelpCommand;
import rotodo.commands.ListCommand;
import rotodo.commands.MarkCommand;
import rotodo.exception.IncompleteInputException;
import rotodo.exception.InvalidInputException;

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
    private static final String EXIT = "bye";

    /**
     * Process user inputs
     * 
     * @param input user input
     * @throws InvalidInputException
     */
    public static Command process(String input) throws InvalidInputException {
        String[] token = input.split(" ", 2);
        boolean delete = false;
        boolean mark = false;
        
        String output = "";
        switch (token[0]) {
        case LIST:
            return new ListCommand();

        case EXIT:
            return new ExitCommand();

        case DELETE:
            if (token.length < 2) {
                throw new IncompleteInputException(
                    "RoTodo need task number");
            }
            try {
                int idx = Integer.parseInt(token[1].split(" ")[0]);
                return new DeleteCommand(idx-1);
            } catch (NumberFormatException e) {
                throw new InvalidInputException(String.format("'%s' not a "
                    + "number RoTodo knows (and RoTodo know much numbers, "
                    + "like 1s and 0s)", token[1]));
            }
        
        case MARK:
            if (token.length < 2) {
                throw new IncompleteInputException(
                    "RoTodo need task number");
            }
            try {
                int idx = Integer.parseInt(token[1].split(" ")[0]);
                return new MarkCommand(idx-1, true);
            } catch (NumberFormatException e) {
                throw new InvalidInputException(String.format("'%s' not a "
                    + "number RoTodo knows (and RoTodo know much numbers, "
                    + "like 1s and 0s)", token[1]));
            }

        case UNMARK:
            if (token.length < 2) {
                throw new IncompleteInputException(
                    "RoTodo need task number");
            }
            try {
                int idx = Integer.parseInt(token[1].split(" ")[0]);
                return new MarkCommand(idx-1, false);
            } catch (NumberFormatException e) {
                throw new InvalidInputException(String.format("'%s' not a "
                    + "number RoTodo knows (and RoTodo know much numbers, "
                    + "like 1s and 0s)", token[1]));
            }

        case TODO:
            if (token.length < 2) {
                throw new IncompleteInputException(
                    "RoTodo can't read you mind, otherwise "
                    + "RoTodo's creator would be rich!\n"
                    + "  RoTodo needs a task description");
            }
            return new AddCommand(AddCommand.TaskType.TODO, token[1]);
        
        case DEADLINE:
            if (token.length < 2) {
                throw new IncompleteInputException(
                    "RoTodo can't read you mind, otherwise "
                    + "RoTodo's creator would be rich!\n"
                    + "  RoTodo needs a task description and deadline");
            }
            token = token[1].split(" /by ", 2);
            if (token.length < 2) {
                throw new IncompleteInputException(
                    "RoTodo can't read you mind, otherwise "
                    + "RoTodo's creator would be rich!\n"
                    + "  RoTodo needs a task description and deadline");
            }
            return new AddCommand(AddCommand.TaskType.DEADLINE, token[0], token[1]);

        case EVENT:
            if (token.length < 2) {
                throw new IncompleteInputException(
                    "RoTodo can't read you mind, otherwise "
                    + "RoTodo's creator would be rich!\n"
                    + "  RoTodo needs a task description, from and to date/time");
            }
            token = token[1].split(" /from | /to ", 3);
            if (token.length < 3) {
                throw new IncompleteInputException(
                    "RoTodo can't read you mind, otherwise "
                    + "RoTodo's creator would be rich!\n"
                    + "  RoTodo needs a task description, from and to date/time");
            }
            return new AddCommand(AddCommand.TaskType.EVENT, token[0], token[1], token[2]);
        
        case HELP:
            return new HelpCommand();
    
        default:
            throw new InvalidInputException(
                "Reep Roop... RoTodo Read No Understand?");
        }
    }
}
