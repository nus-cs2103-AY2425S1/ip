package jude;

import jude.command.AddCommand;
import jude.command.Command;
import jude.command.DeleteCommand;
import jude.command.ExitCommand;
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
    private String command;
    private String[] descriptions;

    public Parser() {
    }

    /** Identifies the Command from the user input and returns the identified command.
     * throws a JudeException if the command is not valid.
     *
     * @param input
     * @return Command from the user.
     * @throws JudeException if the command is not valid, containing the possible reason for invalidity.
     */
    public Command parse(String input) throws JudeException {
        int index;
        Task task;

        // Handle null input.
        if (input == null) {
            throw new JudeException("Poyo, invalid input. Try again...");
        }

        // Perform split of jude.command, and a description, if present.
        String[] split = input.split(" ", 2);
        this.command = split[0];

        // Handle inputs with only a jude.command (and without a description).
        if (command.equals("bye")) {

            if (split.length > 1) {
                throw new JudeException("Poyo,  The description of a " + command + " must be empty.");
            }
            return new ExitCommand();
        } else if (command.equals("list")) {

            if (split.length > 1) {
                throw new JudeException("Poyo,  The description of a " + command + " must be empty.");
            }
            return new ListCommand();
        }
        try {
            if (command.equals("mark")) {
                // Create an array with 1 element (without using split.)
                index = Integer.parseInt(split[1]);
                return new MarkCommand(index);
            }
            if (command.equals("unmark")) {
                // Create an array with 1 element (without using split.)
                index = Integer.parseInt(split[1]);
                return new UnmarkCommand(index);
            }
            if (command.equals("delete")) {
                // Create an array with 1 element (without using split.)
                index = Integer.parseInt(split[1]);
                return new DeleteCommand(index);
            }
        } catch (NumberFormatException ne) {
            throw new JudeException("Number format exception has occurred.");
        } catch (IndexOutOfBoundsException ie) {
            throw new JudeException("the index is not provided or not applicable to the current list size.");
        }

        if (command.equals("to-do")) {
            // Create an array with 1 element (without using split.)
            try {
                return new AddCommand(new Todo(split[1]));
            } catch (ArrayIndexOutOfBoundsException ae) {
                throw new JudeException("No description was provided.");
            }

        } else if (command.equals("deadline")) {

            descriptions = split[1].split(" /by ", 2);

            if (descriptions.length != 2) {
                throw new JudeException("Poyo, the jude.command "
                        + command + " must be provided with a description with the use of /by jude.command.");
            }
            return new AddCommand(new Deadline(descriptions[0], descriptions[1]));

        } else if (command.equals("event")) {

            descriptions = split[1].split(" /from | /to ", 3);

            if (descriptions.length != 3) {
                throw new JudeException("Poyo, the jude.command " + command
                        + " must be provided with a description with the use of /from followed by /to jude.command.");
            }
            return new AddCommand(new Event(descriptions[0], descriptions[1], descriptions[2]));
        }

        throw new JudeException("No valid jude.command was provided.");
    }
}
