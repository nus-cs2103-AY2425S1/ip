package edith;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import edith.exception.MissingDeadlineException;
import edith.exception.MissingEventDurationException;
import edith.exception.MissingKeywordException;
import edith.exception.MissingTaskNameException;
import edith.exception.MissingTaskNumberException;

/**
 * This class handles all parsing of Strings into appropriate outputs.
 */
public class Parser {
    /**
     * Class for commands.
     */
    public enum Command {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        BYE,
        INVALID
    }

    /**
     * Returns command to be executed.
     * @param userInput User input to be deciphered.
     * @return Command as a string.
     */
    public static Command getCommand(String userInput) {
        List<String> userInputs = Arrays.asList(userInput.split(" "));
        return switch (userInputs.get(0)) {
        case "mark" -> Command.MARK;
        case "unmark" -> Command.UNMARK;
        case "list" -> Command.LIST;
        case "todo" -> Command.TODO;
        case "deadline" -> Command.DEADLINE;
        case "event" -> Command.EVENT;
        case "delete" -> Command.DELETE;
        case "find" -> Command.FIND;
        case "bye" -> Command.BYE;
        default -> Command.INVALID;
        };
    }

    /**
     * Returns the task number based on userInput.
     * @param userInput User input to be deciphered.
     * @return task number as an integer.
     */
    public static int getTaskNumber(String userInput) throws MissingTaskNumberException {
        try {
            List<String> userInputs = Arrays.asList(userInput.split(" "));
            int taskNumber = Integer.parseInt(userInputs.get(1));
            return taskNumber;
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskNumberException();
        }
    }

    /**
     * Returns task details including task name and task deadline/duration.
     * @param userInput User input to be deciphered.
     * @param taskType Type of task (Todo, Deadline or Event).
     * @return Task details in a string.
     * @throws MissingTaskNameException When task name is not specified.
     */
    public static String getTaskDetails(String userInput, Command taskType) throws MissingTaskNameException {
        try {
            String type;
            if (taskType.equals(Command.TODO)) {
                type = "todo";
            } else if (taskType.equals(Command.DEADLINE)) {
                type = "deadline";
            } else {
                type = "event";
            }
            List<String> userInputs = Arrays.asList(userInput.split(type + " "));
            return userInputs.get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskNameException();
        }
    }

    /**
     * Returns task name
     * @param taskDetails Details of task in a string including task name and task deadline/duration if applicable.
     * @param taskType Type of task (Todo, Deadline or Event).
     * @return Task name in a string.
     */
    public static String getTaskName(String taskDetails, String taskType) {
        List<String> userInputs;
        if (Objects.equals(taskType, "deadline")) {
            userInputs = Arrays.asList(taskDetails.split(" /by "));
        } else {
            userInputs = Arrays.asList(taskDetails.split(" /from "));
        }
        return userInputs.get(0);
    }

    /**
     * Returns task deadline (only applicable to tasks of class DeadlineTask).
     * @param taskDetails Details of task in a string including task name and task deadline.
     * @return Task deadline in a string.
     * @throws MissingDeadlineException When task deadline is not specified.
     */
    public static String getTaskDeadline(String taskDetails) throws MissingDeadlineException {
        try {
            List<String> userInputs = Arrays.asList(taskDetails.split(" /by "));
            return userInputs.get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingDeadlineException();
        }
    }

    /**
     * Returns task deadline (only applicable to tasks of class EventTask).
     * @param taskDetails Details of task in a string including task name and task duration.
     * @return Task duration in a string.
     * @throws MissingEventDurationException When task duration is not specified.
     */
    public static String getTaskDuration(String taskDetails) throws MissingEventDurationException {
        try {
            List<String> userInputs = Arrays.asList(taskDetails.split(" /from "));
            return userInputs.get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingEventDurationException();
        }
    }

    /**
     * Returns task start date and/or time (only applicable to tasks of class EventTask).
     * @param taskDuration Duration of task in a string including start and end date/time.
     * @return Task start date and/or time in a string.
     * @throws MissingEventDurationException When task duration is not specified.
     */
    public static String getTaskStart(String taskDuration) throws MissingEventDurationException {
        try {
            List<String> userInputs = Arrays.asList(taskDuration.split(" /to "));
            return userInputs.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingEventDurationException();
        }
    }

    /**
     * Returns task end date and/or time (only applicable to tasks of class EventTask).
     * @param taskDuration Duration of task in a string including start and end date/time.
     * @return Task end date and/or time in a string.
     * @throws MissingEventDurationException When task duration is not specified.
     */
    public static String getTaskEnd(String taskDuration) throws MissingEventDurationException {
        try {
            List<String> userInputs = Arrays.asList(taskDuration.split(" /to "));
            return userInputs.get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingEventDurationException();
        }
    }

    /**
     * Returns the keyword user is trying to search.
     * @param userInput User input.
     * @return keyword.
     * @throws MissingKeywordException When keyword is missing from user input.
     */
    public static String getKeyword(String userInput) throws MissingKeywordException {
        try {
            List<String> userInputs = Arrays.asList(userInput.split("find "));
            return userInputs.get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingKeywordException();
        }
    }
}
