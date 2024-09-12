package kietwoforone.parser;

import kietwoforone.commands.*;
import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.tasks.Deadline;
import kietwoforone.tasks.Event;
import kietwoforone.tasks.Todo;
import java.time.DateTimeException;

/**
 * Represents the logic through which the chatbot understands and carries out instructions
 * input by the user.
 */
public class Parser {

    /**
     * Represents the valid commands that can be inputed by the user.
     */
    public enum Instructions {
        LIST, MARK, UNMARK, BYE, TODO, EVENT, DEADLINE, DELETE, DATE, FIND
    }

    /**
     * Throws a KieTwoForOne exception when the user only inputs a command that is
     * not "list" or "bye" with no additional details.
     *
     * @param input
     * @return Boolean
     * @throws KieTwoForOneException
     */
    public static boolean isCompleteInput(String[] input) throws KieTwoForOneException {
        if (input.length < 2 && !input[0].equalsIgnoreCase("list") &&
                !input[0].equalsIgnoreCase("bye")) {
            throw new KieTwoForOneException("Your instruction is incomplete!");
        }
        return true;
    }

    /**
     * Throws a KieTwoForOne exception when the user adds an incorrect event with missing details.
     *
     * @param input
     * @return Boolean
     * @throws KieTwoForOneException
     */
    public static boolean isCompleteEventInput(String[] input) throws KieTwoForOneException {
        if (input.length != 3) {
            throw new KieTwoForOneException("Please input a start and end time!");
        }
        return true;
    }

    /**
     * Throws a KieTwoForOne exception when the user adds an incorrect deadline with missing details.
     *
     * @param input
     * @return Boolean
     * @throws KieTwoForOneException
     */
    public static boolean isCompleteDeadlineInput(String[] input) throws KieTwoForOneException {
        if (input.length != 2) {
            throw new KieTwoForOneException("Please input a deadline!");
        }
        return true;
    }

    /**
     * Returns a new command object depending on the specified keywords in the command string.
     * Throws a KieTwoForOne exception when an invalid command string is inputted by the user.
     *
     * @param command
     * @return Command
     * @throws KieTwoForOneException
     */
    public static Command parse(String command) throws KieTwoForOneException {
        String[] instruction = command.split(" ", 2);
        String[] taskDetails = new String[0];

        try {
            Instructions.valueOf(instruction[0].toUpperCase());
            isCompleteInput(instruction);
        } catch (IllegalArgumentException e) {
            throw new KieTwoForOneException("Invalid input!");
        } catch (KieTwoForOneException e) {
            throw new KieTwoForOneException(e.getMessage());
        }

        if (instruction.length > 1) {
            taskDetails = instruction[1].split(" /", 0);
        }

        switch (Instructions.valueOf(instruction[0].toUpperCase())) {
        case LIST:
            return new ListCommand();
        case BYE:
            return new ByeCommand();
        case MARK:
            return new MarkCommand(Integer.valueOf(instruction[1]));
        case UNMARK:
            return new UnmarkCommand(Integer.valueOf(instruction[1]));
        case TODO:
            return new AddCommand(new Todo(instruction[1]));
        case EVENT:
            try  {
                isCompleteEventInput(taskDetails);
            } catch (KieTwoForOneException e) {
                throw new KieTwoForOneException(e.getMessage());
            }
            try {
                return new AddCommand(new Event(taskDetails[0], taskDetails[1], taskDetails[2]));
            } catch (DateTimeException e) {
                throw new KieTwoForOneException("Date must be valid and in the form YYYY-MM-DD!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new KieTwoForOneException("Please input a date and time for your start and end time!");
            }
        case DEADLINE:
            try {
                isCompleteDeadlineInput(taskDetails);
            } catch (KieTwoForOneException e) {
                throw new KieTwoForOneException(e.getMessage());
            }
            try {
                return new AddCommand(new Deadline(taskDetails[0], taskDetails[1]));
            } catch (DateTimeException e) {
                throw new KieTwoForOneException("Date must be valid and in the form YYYY-MM-DD!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new KieTwoForOneException("Please input a date and time for your deadline!");
            }
        case DELETE:
            return new DeleteCommand(Integer.valueOf(instruction[1]));
        case DATE:
            try {
                return new FindDateCommand(instruction[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new KieTwoForOneException("Please input a valid date!");
            }
        default:
            break;
        }
        return new ByeCommand();
    }

}
