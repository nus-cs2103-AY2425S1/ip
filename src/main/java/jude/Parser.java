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
        int index;

        String command;
        String[] descriptions;

        // Handle null input.
        if (input == null) {
            throw new JudeException("Poyo, invalid input. Try again...");
        }

        // Perform split of command, and a description, if present.
        String[] split = input.split(" ", 2);
        command = split[0];

        try {
            switch (command) {
            case "bye":
                if (split.length != 1) {
                    throw new JudeException("Poyo, the description of a " + command + " must be empty.");
                }
                return new ExitCommand();
            case "list":
                if (split.length != 1) {
                    throw new JudeException("Poyo, the description of a " + command + " must be empty.");
                }
                return new ListCommand();
            case "mark":
                index = Integer.parseInt(split[1]);
                return new MarkCommand(index - 1);
            case "unmark":
                index = Integer.parseInt(split[1]);
                return new UnmarkCommand(index - 1);
            case "delete":
                index = Integer.parseInt(split[1]);
                return new DeleteCommand(index - 1);
            case "find":
                return new FindCommand(split[1]);
            case "to-do":
                return new AddCommand(new Todo(split[1]));
            case "deadline":
                descriptions = split[1].split(" /by ", 2);
                return new AddCommand(new Deadline(descriptions[0], descriptions[1]));
            case "event":
                descriptions = split[1].split(" /from | /to ", 3);
                return new AddCommand(new Event(descriptions[0], descriptions[1], descriptions[2]));
            default:
                throw new JudeException("No valid command was provided.");
            }

        } catch (NumberFormatException ne) {
            throw new JudeException("Invalid number format: expected an integer.");
        } catch (ArrayIndexOutOfBoundsException ae) {
            throw new JudeException("Poyo, the format of the command "
                    + command + " was not valid. The correct format: " + command);
        }
    }
}

