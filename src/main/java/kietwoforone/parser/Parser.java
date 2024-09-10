package kietwoforone.parser;

import kietwoforone.tasks.Deadline;
import kietwoforone.tasks.Event;
import kietwoforone.tasks.Todo;
import kietwoforone.commands.*;
import kietwoforone.exceptions.KieTwoForOneException;
import java.time.DateTimeException;

public class Parser {

    public enum Instructions {
        LIST, MARK, UNMARK, BYE, TODO, EVENT, DEADLINE, DELETE, DATE, FIND
    }

    public static boolean isCompleteInput(String[] input) throws KieTwoForOneException {
        if (input.length < 2 && !input[0].equalsIgnoreCase("list") && !input[0].equalsIgnoreCase("bye")) {
            throw new KieTwoForOneException("Your instruction is incomplete!");
        }
        return true;
    }

    public static boolean isCompleteEventInput(String[] input) throws KieTwoForOneException {
        if (input.length != 3) {
            throw new KieTwoForOneException("Please input a start and end time!");
        }
        return true;
    }

    public static boolean isCompleteDeadlineInput(String[] input) throws KieTwoForOneException {
        if (input.length != 2) {
            throw new KieTwoForOneException("Please input a deadline!");
        }
        return true;
    }

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
            case FIND:
                return new FindWordCommand(instruction[1]);
            default:
                break;
        }
        return new ByeCommand();
    }

}
