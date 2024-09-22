package spike.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import spike.Spike;
import spike.commands.AddTaskCommand;
import spike.commands.ByeCommand;
import spike.commands.Command;
import spike.commands.DeleteTaskCommand;
import spike.commands.ErrorCommand;
import spike.commands.FindCommand;
import spike.commands.InputType;
import spike.commands.ListByDateCommand;
import spike.commands.ListCommand;
import spike.commands.MarkCommand;
import spike.commands.UnmarkCommand;
import spike.commands.UpdateTaskCommand;
import spike.exceptions.SpikeException;
import spike.tasks.Deadline;
import spike.tasks.Event;
import spike.tasks.ToDo;

/**
 * Represents a parser that parses user input and returns the corresponding command.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param command User input.
     * @return Corresponding command.
     * @throws SpikeException If the user input is invalid.
     */
    public static Command parse(String command) throws SpikeException {
        assert command != null : "Command should not be null";
        try {
            String[] inputSplit = command.split(" ", 2);
            InputType inputType = parseInput(inputSplit[0]);

            switch (inputType) {
            case FIND:
                return findTask(inputSplit[1]);
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case LISTBYDATE:
                return new ListByDateCommand();
            case MARK:
                return markTask(inputSplit[1]);
            case UNMARK:
                return unmarkTask(inputSplit[1]);
            case DELETE:
                return deleteTask(inputSplit[1]);
            case UPDATE:
                return updateTask(inputSplit[1]);
            case TODO:
                checkDescription(inputSplit, "todo");
                return addToDo(inputSplit[1]);
            case DEADLINE:
                checkDescription(inputSplit, "deadline");
                return addDeadline(inputSplit[1]);
            case EVENT:
                checkDescription(inputSplit, "event");
                return addEvent(inputSplit[1]);
            default:
                return new ErrorCommand("Please enter a valid command");
            }
        } catch (SpikeException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    private static InputType parseInput(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return InputType.BYE;
        } else if (input.equalsIgnoreCase("list")) {
            return InputType.LIST;
        } else if (input.equalsIgnoreCase("listbydate")) {
            return InputType.LISTBYDATE;
        } else if (input.equalsIgnoreCase("mark")) {
            return InputType.MARK;
        } else if (input.equalsIgnoreCase("unmark")) {
            return InputType.UNMARK;
        } else if (input.equalsIgnoreCase("delete")) {
            return InputType.DELETE;
        } else if (input.equalsIgnoreCase("update")) {
            return InputType.UPDATE;
        } else if (input.equalsIgnoreCase("todo")) {
            return InputType.TODO;
        } else if (input.equalsIgnoreCase("deadline")) {
            return InputType.DEADLINE;
        } else if (input.equalsIgnoreCase("event")) {
            return InputType.EVENT;
        } else if (input.equalsIgnoreCase("find")) {
            return InputType.FIND;
        } else {
            return InputType.ERROR;
        }
    }

    private static void checkDescription(String[] inputArray, String inputType) throws SpikeException {
        assert inputArray != null : "Input array should not be null";
        assert inputType != null : "Input type should not be null";
        if ((inputArray.length == 1) || (inputArray[1].isEmpty())) {
            throw new SpikeException("The description of a " + inputType + " cannot be empty.");
        }
    }

    private static Command markTask(String input) throws SpikeException {
        try {
            int index = Integer.parseInt(input) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new SpikeException("Please enter a valid number");
        }
    }

    private static Command unmarkTask(String input) throws SpikeException {
        try {
            int index = Integer.parseInt(input) - 1;
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new SpikeException("Please enter a valid number");
        }
    }

    private static Command deleteTask(String input) throws SpikeException {
        try {
            int index = Integer.parseInt(input) - 1;
            return new DeleteTaskCommand(index);
        } catch (NumberFormatException e) {
            throw new SpikeException("Please enter a valid number");
        }
    }

    private static Command updateTask(String input) throws SpikeException {
        try {
            int index = Integer.parseInt(input) - 1;
            return new UpdateTaskCommand(index);
        } catch (NumberFormatException e) {
            throw new SpikeException("Please enter a valid number in the format 'update <task number>'");
        }
    }

    /**
     * Checks if the user input is valid for that specific task type.
     *
     * @param updateType  The type of update to be made.
     *                    Can be "description", "date time" or "end date time".
     * @param input The updated part of the task.
     * @return User input for the updated task if it is valid.
     */
    public static String validateUpdatedValue(String updateType, String input) throws SpikeException {
        String trimmedInput = input.trim();
        switch (updateType) {
        case "description":
            if (trimmedInput.isEmpty()) {
                throw new SpikeException("Please enter a valid description");
            }
            return trimmedInput;
        case "date time", "end date time":
            try {
                LocalDateTime updated = LocalDateTime.parse(trimmedInput);
                return trimmedInput;
            } catch (DateTimeException e) {
                throw new SpikeException("Please enter a valid date and time in the format 'yyyy-MM-ddTHH:mm'");
            }
        default:
            throw new SpikeException("Please enter a valid option");
        }
    }

    /**
     * Checks if the user input is valid for that specific task type.
     *
     * @param taskType The type of task to be updated.
     * @param input The type of update to be made.
     * @return User input for the updated task if it is valid.
     */
    public static String validateUpdateType(String taskType, String input) throws SpikeException {
        if (taskType.equals("deadline") && input.equals("3")) {
            throw new SpikeException("Updating 'end date time' is not valid for deadlines.");
        }
        switch (input) {
        case "1":
            return "description";
        case "2":
            return "date time";
        case "3":
            if (taskType.equals("event")) {
                return "end date time";
            } else {
                throw new SpikeException("Invalid option.");
            }
        default:
            throw new SpikeException("Please enter a valid option (1, 2, or 3).");
        }
    }

    private static Command addToDo(String input) throws SpikeException {
        ToDo toDo = new ToDo(input);
        return new AddTaskCommand(toDo);
    }

    private static Command addEvent(String input) throws SpikeException {
        String[] parts = getEventParts(input);
        assert parts.length == 3 : "Event should have a description, start date and end date";
        try {
            LocalDateTime start = LocalDateTime.parse(parts[1].trim());
            LocalDateTime end = LocalDateTime.parse(parts[2].trim());
            if (start.isAfter(end)) {
                throw new SpikeException("Please enter a valid event with a start date and time "
                        + "that is before the end date and time");
            }
            Event formattedEvent = new Event(parts[0].trim(), start, end);
            return new AddTaskCommand(formattedEvent);
        } catch (DateTimeException e) {
            throw new SpikeException("Please enter a valid event with a start date and time "
                    + "followed by /to end date and time");
        }
    }

    private static String[] getEventParts(String input) throws SpikeException {
        String[] parts = input.split(" /from | /to ");
        int correctLength = 3;
        String description = parts[0].trim();
        String startDateTime = parts[1].trim();
        String endDateTime = parts[2].trim();

        if (parts.length != correctLength || description.isEmpty() || startDateTime.isEmpty() || endDateTime.isEmpty()) {
            if (description.isEmpty()) {
                throw new SpikeException("Please enter a valid event description followed by "
                        + "/from yyyy-MM-dd'T'HH:mm:ss <start date, time> "
                        + "/to yyyy-MM-dd'T'HH:mm:ss <end date, time>");
            }
            throw new SpikeException("Please enter a valid event in the right format: event description "
                    + "/from yyyy-MM-dd'T'HH:mm:ss <start date, time> "
                    + "/to yyyy-MM-dd'T'HH:mm:ss <end date, time>");
        }
        return parts;
    }

    private static Command addDeadline(String input) throws SpikeException {
        String[] parts = getDeadlineParts(input);
        assert parts.length == 2 : "Deadline should have a description and a due date";
        try {
            LocalDateTime deadline = LocalDateTime.parse(parts[1].trim());
            Deadline formattedDeadline = new Deadline(parts[0].trim(), deadline);
            return new AddTaskCommand(formattedDeadline);
        } catch (DateTimeException e) {
            throw new SpikeException("Please enter a deadline with a valid due date and time");
        }
    }

    private static String[] getDeadlineParts(String input) throws SpikeException {
        String[] parts = input.split(" /by ", 2);
        int correctLength = 2;
        String description = parts[0].trim();
        String byDateTime = parts[1].trim();

        if (parts.length != correctLength || description.isEmpty() || byDateTime.isEmpty()) {
            if (description.isEmpty()) {
                throw new SpikeException("Please enter a valid deadline description followed by "
                        + "/by yyyy-MM-dd'T'HH:mm:ss <due date, time>");
            }
            throw new SpikeException("Please enter a valid deadline in the right format: "
                    + "deadline description /by yyyy-MM-dd'T'HH:mm:ss <due date, time>");
        }
        return parts;
    }

    private static Command findTask(String input) throws SpikeException {
        if (input.isEmpty()) {
            throw new SpikeException("Please enter a keyword to search for");
        }
        assert !input.isEmpty() : "Keyword should not be empty";
        return new FindCommand(input);
    }
}
