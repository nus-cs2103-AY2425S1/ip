package fishman.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import fishman.exception.FishmanException;
import fishman.exception.FishmanException.InvalidArgumentsException.ErrorType;
import fishman.task.Deadline;
import fishman.task.Event;
import fishman.task.Task;
import fishman.task.TaskList;
import fishman.task.ToDo;



/**
 * The storage class is used to handle the storage of tasks to and from a save file.
 */
public class Storage {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    private final Path filePath;
    private List<String> errorMessages = new ArrayList<>();


    /**
     * Constructs a new Storage object with the provided file path and creates the file if it does not exist.
     *
     * @param filePath The path of the save file used to store tasks.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path should not be null";
        this.filePath = Paths.get(filePath);
        createFileIfDoesNotExist();
    }

    /**
     * Creates the ave file and its parent directories if they do not exist.
     * It handles any IOException that occurs during the file creation process.
     */
    private void createFileIfDoesNotExist() {
        try {
            Files.createDirectories(filePath.getParent());
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
                assert Files.exists(filePath) : "File should exist after attempts to create it";
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Saves the list of tasks to the data file as specified by the filepath. The tasks are converted to
     * CSV string format before being written to the file. If there are error lines, they
     * are preserved, and the valid tasks are written to the file.
     *
     * @param validTasks The list of valid tasks to be written to the save file.
     * @param errorLines A list of error lines to be preserved in the file.
     * @throws RuntimeException If an error occurs while writing to the file.
     */
    public void save(TaskList validTasks, List<String> errorLines) {
        assert validTasks != null : "Valid tasks should not be null";
        assert filePath != null : "File path should not be null";

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            if (errorLines == null || errorLines.isEmpty()) {
                saveValidTasks(validTasks, writer);
            } else {
                saveAllLinesWithErrors(validTasks, errorLines, writer);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error saving tasks to file: " + e.getMessage(), e);
        }
    }

    private void saveValidTasks(TaskList validTasks, BufferedWriter writer) throws IOException {
        for (Task task : validTasks) {
            writer.write(toCsv(task));
            writer.newLine();
        }
    }

    private void saveAllLinesWithErrors(TaskList validTasks,
                                        List<String> errorLines, BufferedWriter writer) throws IOException {

        saveValidTasks(validTasks, writer);
        for (String errorLine : errorLines) {
            writer.write(errorLine);
            writer.newLine();
        }
    }

    /**
     * Converts a Task object to a CSV string representation, with "|" as the delimiter.
     *
     * @param task The task to be converted to CSV format.
     * @return A string representing the task in CSV format.
     */
    private String toCsv(Task task) {
        assert task != null : "Task should not be null";

        StringBuilder sb = new StringBuilder();
        if (task instanceof ToDo) {
            sb.append("T").append(task.getTaskStatus() ? "|true" : "|false").append("|")
                    .append(task.getTaskDescription());
        } else if (task instanceof Deadline) {
            sb.append("D").append(task.getTaskStatus() ? "|true" : "|false").append("|")
                    .append(task.getTaskDescription())
                            .append("|")
                            .append(((Deadline) task).getDeadlineDate().format(DATE_TIME_FORMATTER));
        } else if (task instanceof Event) {
            sb.append("E").append(task.getTaskStatus() ? "|true" : "|false").append("|")
                            .append(task.getTaskDescription())
                            .append("|")
                            .append(((Event) task).getEventStart().format(DATE_TIME_FORMATTER)).append("|")
                            .append(((Event) task).getEventEnd().format(DATE_TIME_FORMATTER));
        }
        return sb.toString();
    }

    /**
     * Loads the tasks from the file specified by the filepath. Each line is read and parsed into the
     * corresponding Task object. If any corrupted lines are detected during parsing, the invalid lines are skipped,
     * and the error messages are collected. Returns a LoadResult Object with valid tasks, corrupted lines
     * and any error messages.
     *
     * @return A LoadResults object containing all valid tasks, corrupted lines, and any error messages.
     * @throws RuntimeException If any other errors occurs while reading the file.
     */
    public LoadResults load() {
        assert filePath != null : "File path should not be null";
        TaskList validTasks = new TaskList();
        List<String> corruptedLines = new ArrayList<>();
        errorMessages.clear();

        try {
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                try {
                    processLine(line, validTasks);
                } catch (FishmanException e) {
                    errorMessages.add(e.getMessage());
                    corruptedLines.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }

        String combinedErrorMessage = errorMessages.isEmpty() ? null
                : String.join("\n", errorMessages) + "\nInvalid data lines will be skipped. "
                + "Please check the data file!";

        return new LoadResults(validTasks, combinedErrorMessage, corruptedLines);
    }

    /**
     * Processes a line from the data file, parsing it to the corresponding Task object
     * and adding it to the valid task list.
     *
     * @param line The line to be processed.
     * @param validTasks The list of valid tasks which tasks will be added to.
     * @throws FishmanException If the line cannot be parsed into a task.
     */
    private void processLine(String line, TaskList validTasks) throws FishmanException {
        String[] arguments = line.split("\\|", -1);

        if (arguments.length < 3) {
            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_ARGUMENTS, line);
        }

        String type = arguments[0].trim();
        String isDoneStr = arguments[1].trim();
        String description = arguments[2].trim();

        boolean isDone = parseIsTaskDone(isDoneStr, line);

        switch (type) {
        case "T":
            validateToDoArguments(arguments, description, line);
            validTasks.addTask(new ToDo(description, isDone));
            break;
        case "D":
            LocalDateTime deadlineDate = parseDeadlineArguments(arguments, description, line);
            validTasks.addTask(new Deadline(description, isDone, deadlineDate));
            break;
        case "E":
            LocalDateTime[] eventDates = parseEventArguments(arguments, description, line);
            validTasks.addTask(new Event(description, isDone, eventDates[0], eventDates[1]));
            break;
        default:
            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_TASK_TYPE, line);
        }
    }

    /**
     * Parses the "isTaskDone" status from a string representation in the date file.
     *
     * @param isTaskDoneStr The string representation of the "isTaskDone" status.
     * @param line The line being processed.
     * @return A boolean indicating whether the task is done.
     * @throws FishmanException If the string representation is neither "true" nor "false"
     */
    private boolean parseIsTaskDone(String isTaskDoneStr, String line) throws FishmanException {
        if (isTaskDoneStr.equalsIgnoreCase("true")) {
            return true;
        } else if (isTaskDoneStr.equalsIgnoreCase("false")) {
            return false;
        } else {
            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_IS_DONE, line);
        }
    }

    /**
     * Validates the arguments for a to-do task.
     *
     * @param arguments The arguments of the line being processed.
     * @param description The description of the to-do task.
     * @param line The line being processed.
     * @throws FishmanException If the arguments are invalid for a to-do task.
     */
    private void validateToDoArguments(String[] arguments, String description, String line) throws FishmanException {
        if (arguments.length != 3 || description.isEmpty()) {
            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_TODO, line);
        }
    }

    /**
     * Parses the arguments for a Deadline task and returns the deadline date.
     *
     * @param arguments The arguments of the line being processed.
     * @param description The description of the Deadline task.
     * @param line The original line being processed.
     * @return The parsed LocalDateTime representing the deadline.
     * @throws FishmanException If the arguments are invalid for a Deadline task.
     */
    private LocalDateTime parseDeadlineArguments(String[] arguments, String description, String line)
            throws FishmanException {
        if (arguments.length != 4 || description.isEmpty()) {
            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_DEADLINE, line);
        }
        String deadline = arguments[3].trim();
        return parseDateTime(deadline, line);
    }

    /**
     * Parses the arguments for an Event task and returns the start and end dates.
     *
     * @param arguments The arguments of the line being processed.
     * @param description The description of the Event task.
     * @param line The line being processed.
     * @return An array of LocalDateTime containing the start and end dates of the event task.
     * @throws FishmanException If the arguments are invalid for an Event task.
     */
    private LocalDateTime[] parseEventArguments(String[] arguments, String description, String line)
            throws FishmanException {
        if (arguments.length != 5 || description.isEmpty()) {
            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_EVENT, line);
        }
        String from = arguments[3].trim();
        String to = arguments[4].trim();
        LocalDateTime eventStart = parseDateTime(from, line);
        LocalDateTime eventEnd = parseDateTime(to, line);
        return new LocalDateTime[]{eventStart, eventEnd};
    }

    /**
     * Parses a date-time string into a LocalDateTime object using the specified date-time formatter.
     * This method attempts to parse the given date-time string using the DATE_TIME_FORMATTER.
     * If the string does not conform to the expected format, a FishmanException is thrown to indicate parsing error.
     *
     * @param dateTimeStr The date-time string to parses.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws FishmanException.InvalidArgumentsException If the input string does not match
     *      the expected date-time format.
     */
    private static LocalDateTime parseDateTime(String dateTimeStr, String line) throws FishmanException {
        assert dateTimeStr != null : "DateTime string should not be null";

        try {
            return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_DATE_FORMAT, line);
        }
    }

    /**
     * Represents the results of loading tasks from the save file.
     */
    public static class LoadResults {
        private final TaskList validTasks;
        private final String errorMessage;
        private final List<String> corruptedLines;

        /**
         * Constructs a new LoadResult Object with the specified parameters
         *
         * @param validTasks The Task List of valid tasks from the file.
         * @param errorMessage The combined error messages, containing the error of every corrupted line.
         * @param corruptedLines The list of corrupted lines from the file.
         */
        public LoadResults(TaskList validTasks, String errorMessage, List<String> corruptedLines) {
            this.validTasks = validTasks;
            this.errorMessage = errorMessage;
            this.corruptedLines = corruptedLines;
        }

        public TaskList getValidTasks() {
            return validTasks;
        }

        public List<String> getCorruptedLines() {
            return corruptedLines;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
