package pikappi;

import pikappi.command.Command;
import pikappi.command.DeadlineCommand;
import pikappi.command.DeleteCommand;
import pikappi.command.EventCommand;
import pikappi.command.ExitCommand;
import pikappi.command.FindCommand;
import pikappi.command.HelpCommand;
import pikappi.command.ListCommand;
import pikappi.command.MarkCommand;
import pikappi.command.SortCommand;
import pikappi.command.TodoCommand;
import pikappi.command.UnmarkCommand;
import pikappi.exception.PikappiException;

/**
 * Represents a parser that handles user input.
 */
public class Parser {
    /**
     * Returns a Command object that corresponds to the user input.
     *
     * @param command User input from command line
     * @return Command object that corresponds to the user input
     * @throws PikappiException If the user input is invalid
     */
    public Command parse(String command) throws PikappiException {
        Command c;
        switch (command.split(" ")[0].toUpperCase()) {
        case "BYE":
            c = new ExitCommand();
            break;
        case "LIST":
            c = new ListCommand();
            break;
        case "MARK":
            c = new MarkCommand(Integer.parseInt(command.split(" ")[1]));
            break;
        case "UNMARK":
            try {
                c = new UnmarkCommand(Integer.parseInt(command.split(" ")[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? Task does not exist..");
            }
            break;
        case "TODO":
            try {
                c = new TodoCommand(command.split("todo ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What is the task?");
            }
            break;
        case "DEADLINE":
            try {
                c = new DeadlineCommand(command.split("deadline ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What is the task?");
            }
            break;
        case "EVENT":
            try {
                c = new EventCommand(command.split("event ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What is the task?");
            }
            break;
        case "DELETE":
            try {
                c = new DeleteCommand(Integer.parseInt(command.split("delete ")[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? Which task do you want to delete?");
            }
            break;
        case "FIND":
            try {
                c = new FindCommand(command.split("find ")[1].split(", "));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What do you want to find?");
            }
            break;
        case "HELP":
            c = new HelpCommand();
            break;
        case "SORT":
            try {
                c = new SortCommand(command.split("sort ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What do you want to sort by?");
            }
            break;
        default:
            throw new PikappiException("Pika..?? I don't understand..");
        }
        return c;
    }
}
