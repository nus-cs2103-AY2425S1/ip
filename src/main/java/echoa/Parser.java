package echoa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser is a class that encapsulates all methods interacting with CLI.
 */

public class Parser {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * The method removes excess white space in all the Strings within the commandArray.
     *
     * @param commandArray Array containing the different Strings of commands.
     */
    public static void trimSplitCommands(String[] commandArray) {
        for (int i = 0; i < commandArray.length; i++) {
            commandArray[i] = commandArray[i].trim();
        }
    }

    /**
     * The method creates a LocalDateTime with the given dateString and timeString.
     *
     * @param dateString String representation of date.
     * @param timeString Time representation of date.
     * @return LocalDateTime of the dateString and timeString.
     * @throws DateTimeParseException when dateString and timeString are not in the correct format.
     */
    public static LocalDateTime createDateTime(String dateString, String timeString) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
        LocalTime time = LocalTime.parse(timeString, TIME_FORMATTER);
        return LocalDateTime.of(date, time);
    }

    /**
     * The method parses the line for specific instructions.
     *
     * @param line line to be parsed.
     * @return instruction keyword.
     */
    public String parseCommand(String line) {
        String command;
        line = line.trim();
        if (line.isBlank()) {
            command = "";
        } else if (line.startsWith("list")) {
            command = "list";
        } else if (line.startsWith("find")) {
            command = "find";
        } else if (line.startsWith("mark")) {
            command = "mark";
        } else if (line.startsWith("unmark")) {
            command = "unmark";
        } else if (line.startsWith("delete")) {
            command = "delete";
        } else if (line.startsWith("todo")) {
            command = "todo";
        } else if (line.startsWith("deadline")) {
            command = "deadline";
        } else if (line.startsWith("event")) {
            command = "event";
        } else if (line.startsWith("bye")) {
            command = "bye";
        } else {
            command = line;
        }
        return command;
    }

    /**
     * The method parses for indexes in lines with instructions:
     * mark, unmark and delete.
     *
     * @param indexString indexString contains a possible label
     * @return the index (0-indexed)
     */
    public int parseIndex(String indexString) throws InvalidIndexInputException {
        String[] lineArray = indexString.split("\\s+");
        int numOfWords = lineArray.length;

        if (numOfWords != 1) {
            throw new InvalidIndexInputException(indexString);
        }

        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(indexString);
        if (!matcher.find()) {
            throw  new InvalidIndexInputException(indexString);
        }

        return Integer.parseInt(indexString) - 1;
    }

    /**
     * The method searches for tasks in the taskList that contains the specified keyword.
     *
     * @param taskList taskList to be searched.
     * @param keyword keyword to be searched.
     * @return TaskList containing tasks containing the keyword.
     */
    public TaskList parseFindTask(TaskList taskList, String keyword) {

        TaskList tasks = new TaskList();

        for (int i = 0; i < taskList.getSize(); i++) {
            Task t = taskList.getSpecificTask(i);
            if (t.getDescription().contains(keyword)) {
                tasks.addTask(t);
            }
        }

        return tasks;
    }

    /**
     * The method parses for description from the task line.
     *
     * @param task task line.
     * @return an array containing description.
     * @throws InvalidToDoContentException when the input format for todo is incorrect.
     */
    public Object[] parseToDoTask(String task) throws InvalidToDoContentException {
        if (task.isBlank() || task.isEmpty()) {
            throw new InvalidToDoContentException();
        }
        return new Object[] {task};
    }

    /**
     * The method parses for description, dateAndTime from the task line.
     *
     * @param task task line
     * @return an array containing description, dateAndTime.
     * @throws InvalidDeadlineContentException when the input format for deadline is incorrect.
     * @throws DateTimeParseException when the input format for date and time is incorrect.
     */
    public Object[] parseDeadlineTask(String task) throws InvalidDeadlineContentException, DateTimeParseException {
        String[] taskArray = task.split("/");
        trimSplitCommands(taskArray);
        if (taskArray.length != 2) {
            throw new InvalidDeadlineContentException();
        }

        String taskDescription = taskArray[0];
        String taskDate = taskArray[1];

        String[] taskDateArray = taskDate.split("\\s+");
        if (taskDateArray.length != 2) {
            throw new InvalidDeadlineContentException();
        }

        String date = taskDateArray[0];
        String time = taskDateArray[1];
        LocalDateTime dateAndTime = createDateTime(date, time);

        return new Object[] {taskDescription, dateAndTime};
    }

    /**
     * The method parses for description, startDateAndTime, endDateAndTime from the task line.
     *
     * @param task task line
     * @return an array containing description, startDateAndTime, endDateAndTime.
     * @throws InvalidEventContentException when the input format for event is not satisfied.
     * @throws DateTimeParseException when the input format for date and time is incorrect.
     */
    public Object[] parseEventTask(String task) throws InvalidEventContentException, DateTimeParseException {
        String[] taskArray = task.split("/");
        trimSplitCommands(taskArray);
        if (taskArray.length != 3) {
            throw new InvalidEventContentException();
        }

        String taskDescription = taskArray[0];
        String taskStart = taskArray[1];
        String taskEnd = taskArray[2];

        String[] taskStartArray = taskStart.split("\\s+");
        String[] taskEndArray = taskEnd.split("\\s+");

        if (taskStartArray.length != 2 || taskEndArray.length != 2) {
            throw new InvalidEventContentException();
        }

        String startDate = taskStartArray[0];
        String startTime = taskStartArray[1];
        LocalDateTime startDateAndTime = createDateTime(startDate, startTime);

        String endDate = taskEndArray[0];
        String endTime = taskEndArray[1];
        LocalDateTime endDateAndTime = createDateTime(endDate, endTime);

        return new Object[] {taskDescription, startDateAndTime, endDateAndTime};
    }
}
