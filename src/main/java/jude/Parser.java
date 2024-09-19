package jude;

import jude.command.AddCommand;
import jude.command.Command;
import jude.command.DeleteCommand;
import jude.command.ExitCommand;
import jude.command.FindCommand;
import jude.command.ListCommand;
import jude.command.MarkCommand;
import jude.command.UnmarkCommand;
import jude.task.Deadline;
import jude.task.Event;
import jude.task.Task;
import jude.task.Todo;

/**
 * It takes in input from the user to return its Command.
 *
 * This class has the method parse to identify the command based on the input of the user.
 * If the input is not valid, it throws JudeException.
 */
public class Parser {

    /**
     * Identifies the Command from the user input and returns the identified command.
     * throws a JudeException if the command is not valid.
     *
     * @param input
     * @return Command from the user.
     * @throws JudeException if the command is not valid, containing the possible reason for invalidity.
     */
    public static Command parse(String input) throws JudeException {

        String header;
        String description;

        // Handle null input
        if (input == null) {
            throw new JudeException("Poyo, invalid input. Try again...");
        }

        // Set header and description
        String[] split = input.split(" ", 2);
        header = split[0];
        description = split.length == 1 ? "" : split[1];

        try {
            return switch (header) {
            case "bye" -> getExitCommand(split, header);
            case "list" -> getListCommand(split, header);
            case "mark" -> getIndexCommand(header, description);
            case "unmark" -> getIndexCommand(header, description);
            case "delete" -> getIndexCommand(header, description);
            case "find" -> new FindCommand(description);
            case "to-do" -> getAddCommand(header, description);
            case "deadline" -> getAddCommand(header, description);
            case "event" -> getAddCommand(header, description);
            default -> throw new JudeException("No valid command was provided.");
            };
        } catch (NumberFormatException ne) {
            throw new JudeException("The command " + header + " has an invalid number format: expected an integer.");
        } catch (ArrayIndexOutOfBoundsException ae) {
            throw new JudeException("Poyo, the format of the command "
                    + header + " was not valid. ");
        }
    }

    private static AddCommand getAddCommand(String header, String description) throws JudeException {
        Task task;
        switch (header) {
        case "to-do":
            task = new Todo(description);
            break;
        case "deadline":
            task = parseDeadlineTask(description);
            break;
        case "event":
            task = parseEventTask(description);
            break;
        default:
            assert false : "Invalid header: " + header; // programmer error: invalid header
            task = null; // initialize task to avoid compiler error
        }
        return new AddCommand(task);
    }

    private static Command getIndexCommand(String header, String description) throws JudeException {
        int index;
        Command command;
        index = parseIndex(description);
        switch (header) {
        case "delete":
            command = new DeleteCommand(index);
            break;
        case "mark":
            command = new MarkCommand(index);
            break;
        case "unmark":
            command = new UnmarkCommand(index);
            break;
        default:
            throw new JudeException("Invalid header");
        }
        return command;
    }

    private static ListCommand getListCommand(String[] split, String header) throws JudeException {
        checkOneWord(split, header);
        return new ListCommand();
    }

    private static ExitCommand getExitCommand(String[] split, String header) throws JudeException {
        checkOneWord(split, header);
        return new ExitCommand();
    }

    private static Event parseEventTask(String description) throws JudeException {
        String[] words = description.split(" /from | /to ", 3);
        return new Event(words[0], words[1], words[2]);
    }
    private static Deadline parseDeadlineTask(String description) throws JudeException {
        String[] words = description.split(" /by ", 2);
        return new Deadline(words[0], words[1]);
    }

    private static void checkOneWord(String[] split, String header) throws JudeException {
        if (split.length != 1) {
            throw new JudeException("Poyo, the description of a " + header + " must be empty.");
        }
    }

    private static int parseIndex(String description) throws JudeException {
        return Integer.parseInt(description) - 1;
    }
}

