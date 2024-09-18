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

    // Regex and formatter below is generated by AI
    private static final String DATE_REGEX = "\\b(\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))\\b";
    private static final String TIME_REGEX = "\\b(?:[01]\\d|2[0-3]):[0-5]\\d\\b";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Returns a String in the given time format.
     *
     * @param s that may contain date in yyyy-MM-dd format
     * @return a String in the yyyy-MM-dd format, null if no date in yyyy-MM-dd is found.
     */
    // Solution below is inspired by AI: Pattern and Matcher
    private static String findDate(String s) {
        String date = null;
        Pattern pattern = Pattern.compile(DATE_REGEX);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            date = matcher.group(1);
        }
        return date;
    }

    /**
     * Returns a String in the HH:MM format.
     *
     * @param s that may contain a time in HH:mm format.
     * @return a String in the HH:MM form, null if no time in HH:mm is found.
     */
    // Solution below is inspired by AI: Pattern and Matcher
    private static String findTime(String s) {
        String time = null;

        Pattern pattern = Pattern.compile(TIME_REGEX);
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            time = matcher.group();
        }

        return time;
    }

    /**
     * Returns a LocalDate of any date found in the input String s.
     * Returns a LocalDate of null if no date is found in the input String s.
     *
     * @param s String which may contain a date.
     * @return LocalDate of the Date in the String or null.
     * @throws DateFormatException if the date is not in the correct date format.
     */
    private static LocalDate getLocalDate(String s) throws DateFormatException {
        LocalDate d = null;
        if (findDate(s) != null) {
            try {
                d = LocalDate.parse(findDate(s), DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new DateFormatException();
            }
        }
        return d;
    }

    /**
     * Returns a LocalTime of any time found in the input String s.
     * Returns a LocalTime of null if no time is found in the input String s.
     *
     * @param s String which may contain a time.
     * @return LocalDate of the Time in the String or null.
     * @throws TimeFormatException if they time is not in the ocrrect time format.
     */
    private static LocalTime getLocalTime(String s) throws TimeFormatException {
        LocalTime t = null;
        if (findTime(s) != null) {
            try {
                t = LocalTime.parse(findTime(s), TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new TimeFormatException();
            }
        }
        return t;
    }

    /**
     * Checks if the given taskLine contains a Date.
     *
     * @param taskLine taskLine which may contain a Date.
     * @return true if taskLine contains Date, and false otherwise.
     */
    private static boolean hasDate(String taskLine) {
        return findTime(taskLine) != null;
    }

    /**
     * Checks if the given taskLine contains a Time.
     *
     * @param taskLine taskLine which may contain a Time.
     * @return true if taskLine contains Time, and false otherwise.
     */
    private static boolean hasTime(String taskLine) {
        return findTime(taskLine) != null;
    }

    /**
     * Checks if the given taskLine contains a Date and Time.
     *
     * @param taskLine taskLine which may contain a Date and Time.
     * @return true if taskLine contains Date and time, and false otherwise.
     */
    private static boolean hasDateAndTime(String taskLine) {
        return hasDate(taskLine) && hasTime(taskLine);
    }

    /**
     * Creates a LocalDateTime with the given dateString and timeString.
     *
     * @param dateString String representation of date.
     * @param timeString Time representation of date.
     * @return LocalDateTime of the dateString and timeString.
     * @throws DateTimeParseException when dateString and timeString are not in the correct format.
     */
    private static LocalDateTime createDateTime(String dateString, String timeString) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
        LocalTime time = LocalTime.parse(timeString, TIME_FORMATTER);
        return LocalDateTime.of(date, time);
    }

    /**
     * Removes excess white space in all the Strings within the commandArray.
     *
     * @param commandArray Array containing the different Strings of commands.
     */
    private static void trimSplitCommands(String[] commandArray) {
        for (int i = 0; i < commandArray.length; i++) {
            commandArray[i] = commandArray[i].trim();
        }
    }

    /**
     * Removes the first occurrence of a given letter in a string.
     *
     * @param s string from which letter has be removed from.
     * @param c letter that has to be removed.
     * @return String without the first occurrence of the letter.
     */
    // Solution below is inspired by AI
    public static String removeFirstOccurrence(String s, char c) {
        int index = s.indexOf(c);
        if (index == -1) {
            return s;
        }
        return s.substring(0, index) + s.substring(index + 1);
    }

    public static String[] splitAndTrimCommand(String taskLine) {
         String[] taskArray = taskLine.split("/");
         trimSplitCommands(taskArray);
         return taskArray;
    }

    public static boolean isCorrectInputFormat(String[] taskArray, String taskType) {
        switch (taskType) {
        case "todo":
            if (taskArray.length != 1) {
                return false;
            }
            break;
        case "deadline":
            if (taskArray.length != 2) {
                return false;
            }
            break;
        case "event":
            if (taskArray.length != 3) {
                return false;
            }
            break;
        default:
            // do nothing
        }
        for (String s : taskArray) {
            if (s.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Parses the line for specific instructions.
     *
     * @param line line to be parsed.
     * @return instruction keyword.
     */
    public Command parseCommand(String line, Ui ui, Parser parser, TaskList taskList, Storage storage) throws InvalidInstructionException {
        line = line.trim();
        Command command;
        if (line.startsWith("hi")) {
            command = new HiCommand(ui, parser, taskList, storage);
        } else if (line.startsWith("list")) {
            command = new ListCommand(ui, parser, taskList, storage);
        } else if (line.startsWith("find")) {
            command = new FindCommand(ui, parser, taskList, storage);
        } else if (line.startsWith("mark")) {
            command = new MarkCommand(ui, parser, taskList, storage);
        } else if (line.startsWith("unmark")) {
            command = new UnmarkCommand(ui, parser, taskList, storage);
        } else if (line.startsWith("delete")) {
            command = new DeleteCommand(ui, parser, taskList, storage);
        } else if (line.startsWith("update")) {
            command = new UpdateCommand(ui, parser, taskList, storage);
        } else if (line.startsWith("todo")) {
            command = new ToDoCommand(ui, parser, taskList, storage);
        } else if (line.startsWith("deadline")) {
            command = new DeadlineCommand(ui, parser, taskList, storage);
        } else if (line.startsWith("event")) {
            command = new EventCommand(ui, parser, taskList, storage);
        } else if (line.startsWith("bye")) {
            command = new ByeCommand(ui, parser, taskList, storage);
        } else if (line.isEmpty()) {
            throw new InvalidInstructionException("Blank");
        } else {
            throw new InvalidInstructionException(line);
        }
        return command;
    }

    /**
     * Parses for indexes in lines with instructions:
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

        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(indexString);
        if (!matcher.find()) {
            throw  new InvalidIndexInputException(indexString);
        }

        return Integer.parseInt(indexString) - 1;
    }

    public int parseUpdateIndex(String indexString) throws InvalidIndexInputException {
        String[] lineArray = indexString.split("\\s+", 2);
        trimSplitCommands(lineArray);

        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(lineArray[0]);
        if (!matcher.find()) {
            throw  new InvalidIndexInputException(indexString);
        }

        return Integer.parseInt(lineArray[0]) - 1;
    }

    /**
     * Returns a Tasklist with tasks containing the specified keyword.
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
     * Parses for description from the task line.
     *
     * @param task task line.
     * @return an array containing description.
     * @throws InvalidToDoContentException when the input format for todo is incorrect.
     */
    public Object[] parseToDoTask(String task) throws InvalidToDoContentException {
        String[] taskArray = splitAndTrimCommand(task);
        if (!isCorrectInputFormat(taskArray, "todo")) {
            throw new InvalidToDoContentException();
        }
        return new Object[]{taskArray[0]};
    }

    /**
     * Parses for description, dateAndTime from the task line.
     *
     * @param task task line
     * @return an array containing description, dateAndTime.
     * @throws InvalidDeadlineContentException when the input format for deadline is incorrect.
     * @throws DateTimeParseException when the input format for date and time is incorrect.
     */
    public Object[] parseDeadlineTask(String task) throws InvalidDeadlineContentException, DateTimeParseException {
        String[] taskArray = splitAndTrimCommand(task);
        if (!isCorrectInputFormat(taskArray, "deadline")) {
            throw new InvalidDeadlineContentException();
        }

        String description = taskArray[0];
        String dateTimeLine = taskArray[1];

        if (!hasDateAndTime(dateTimeLine)) {
            throw new InvalidDeadlineContentException();
        }

        String date = findDate(dateTimeLine);
        String time = findTime(dateTimeLine);
        LocalDateTime dateAndTime = createDateTime(date, time);

        return new Object[] {description, dateAndTime};
    }

    /**
     * Parses for description, startDateAndTime, endDateAndTime from the task line.
     *
     * @param task task line
     * @return an array containing description, startDateAndTime, endDateAndTime.
     * @throws InvalidEventContentException when the input format for event is not satisfied.
     * @throws DateTimeParseException when the input format for date and time is incorrect.
     */
    public Object[] parseEventTask(String task) throws InvalidEventContentException, DateTimeParseException {
        String[] taskArray = splitAndTrimCommand(task);
        if (!isCorrectInputFormat(taskArray, "event")) {
            throw new InvalidEventContentException();
        }

        String description = taskArray[0];
        String startDateTimeLine = taskArray[1];
        String endDateTimeLine = taskArray[2];

        if (!hasDateAndTime(startDateTimeLine) || !hasDateAndTime(endDateTimeLine)) {
            throw new InvalidEventContentException();
        }

        String startDate = findDate(startDateTimeLine);
        String startTime = findTime(startDateTimeLine);
        LocalDateTime startDateAndTime = createDateTime(startDate, startTime);

        String endDate = findDate(endDateTimeLine);
        String endTime = findTime(endDateTimeLine);
        LocalDateTime endDateAndTime = createDateTime(endDate, endTime);

        return new Object[] {description, startDateAndTime, endDateAndTime};
    }

    /**
     * Parses for any description from the toDoUpdates.
     *
     * @param toDoUpdates updated ToDo content
     * @return an array of description
     */
    public Object[] parseToDoUpdate(String toDoUpdates) throws ToDoUpdateFormatException {

        String description = null;

        String[] updatesArray = splitAndTrimCommand(toDoUpdates);

        for (String s : updatesArray) {
            if (s.isEmpty()) {
                continue;
            }
            switch (s.charAt(0)) {
            case 'd':
            s = removeFirstOccurrence(s, 'd').trim();
            description = s;
            break;
            default:
                throw new ToDoUpdateFormatException();
            }
        }

        return new Object[] {description};
    }

    /**
     * Parses for any description, date or time from the deadlineUpdates.
     *
     * @param deadlineUpdates updated Deadline content
     * @return an array of description, date, time
     */
    public Object[] parseDeadlineUpdate(String deadlineUpdates) throws DeadlineUpdateFormatException, DateFormatException, TimeFormatException {

        String description = null;
        LocalDate endDate = null;
        LocalTime endTime = null;

        String[] updatesArray = deadlineUpdates.split("/");
        trimSplitCommands(updatesArray);

        for (String s : updatesArray) {
            if (s.isEmpty()) {
                continue;
            }
            switch (s.charAt(0)) {
            case 'd':
                s = removeFirstOccurrence(s, 'd').trim();
                description = s;
                break;
            case 'e':
                s = removeFirstOccurrence(s, 'e').trim();
                endDate = getLocalDate(s);
                endTime = getLocalTime(s);
                break;
            default:
                throw new DeadlineUpdateFormatException();
            }
        }
        return new Object[] {description, endDate, endTime};
    }

    /**
     * Parses for any description, startDate, startTime, endDate, endTime from the eventUpdates.
     *
     * @param eventUpdates updated Event content
     * @return an array of description, startDate, startTime, endDate, endTime
     */
    public Object[] parseEventUpdate(String eventUpdates) throws EventUpdateFormatException, DateFormatException, TimeFormatException {
        String description = null;
        LocalDate startDate = null;
        LocalTime startTime = null;
        LocalDate endDate = null;
        LocalTime endTime = null;

        String[] updatesArray = eventUpdates.split("/");
        trimSplitCommands(updatesArray);
        for (String s : updatesArray) {
            if (s.isEmpty()) {
                continue;
            }
            switch (s.charAt(0)) {
            case 'd':
                s = removeFirstOccurrence(s, 'd').trim();
                description = s;
                break;
            case 's':
                startDate = getLocalDate(s);
                startTime = getLocalTime(s);
                break;
            case 'e':
                endDate = getLocalDate(s);
                endTime = getLocalTime(s);
                break;
            default:
                throw new EventUpdateFormatException();
            }
        }
        return new Object[] {description, startDate, startTime, endDate, endTime};
    }
}
