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
     * Saves the list of tasks to the save file as specified by the filepath. The tasks are converted to
     * CSV string format before being written to the file. If there are no error messages, the tasks are written to the
     * file from the task list. Otherwise, the original file contents are preserved.
     *
     * @param validTasks The list of tasks to be written to the save file.
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
     * and the error messages are collected. Returns a LoadResult Object with valid tasks, the original file
     * lines and any error messages.
     *
     * @return A LoadResults object containing all valid tasks, original file lines, and any error messages.
     * @throws RuntimeException If any other errors occurs while reading the file.
     */
    public LoadResults load() {
        assert filePath != null : "File path should not be null";
        TaskList  validTasks = new TaskList();
        List<String> corruptedLines = new ArrayList<>();
        errorMessages.clear();
        List<String> allLines;

        try {
            List<String> lines = Files.readAllLines(filePath);
            allLines = new ArrayList<>(lines);

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

        return new LoadResults(validTasks, allLines, combinedErrorMessage, corruptedLines);
    }

    private void processLine(String line, TaskList validTasks) throws FishmanException {
        String[] arguments = line.split("\\|", -1);

        if (arguments.length < 3) {
            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_ARGUMENTS, line);
        }

        String type = arguments[0].trim();
        String isDoneStr = arguments[1].trim();
        String description = arguments[2].trim();

        boolean isDone = parseIsDone(isDoneStr, line);

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

    private boolean parseIsDone(String isDoneStr, String line) throws FishmanException {
        if (isDoneStr.equalsIgnoreCase("true")) {
            return true;
        } else if (isDoneStr.equalsIgnoreCase("false")) {
            return false;
        } else {
            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_IS_DONE, line);
        }
    }

    private void validateToDoArguments(String[] arguments, String description, String line) throws FishmanException {
        if (arguments.length != 3 || description.isEmpty()) {
            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_TODO, line);
        }
    }

    private LocalDateTime parseDeadlineArguments(String[] arguments, String description, String line) throws FishmanException {
        if (arguments.length != 4 || description.isEmpty()) {
            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_DEADLINE, line);
        }
        String deadline = arguments[3].trim();
        return parseDateTime(deadline, line);
    }

    private LocalDateTime[] parseEventArguments(String[] arguments, String description, String line) throws FishmanException {
        if (arguments.length != 5 || description.isEmpty()) {
            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_EVENT, line);
        }
        String from = arguments[3].trim();
        String to = arguments[4].trim();
        LocalDateTime fromDate = parseDateTime(from, line);
        LocalDateTime toDate = parseDateTime(to, line);
        return new LocalDateTime[]{fromDate, toDate};
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
        private final List<String> allFileLines;
        private final List<String> corruptedLines;

        /**
         * Constructs a new LoadResult Object with the specified parameters
         *
         * @param validTasks The Task List of valid tasks from the file.
         * @param allFileLines The list of all lines from the original file.
         * @param errorMessage The combined error messages, containing the error of every corrupted line.
         */
        public LoadResults(TaskList validTasks, List<String> allFileLines, String errorMessage, List<String> corruptedLines) {
            this.validTasks = validTasks;
            this.allFileLines = allFileLines;
            this.errorMessage = errorMessage;
            this.corruptedLines = corruptedLines;
        }

        public TaskList getValidTasks() {
            return validTasks;
        }

        public List<String> getAllTasksLines() {
            return allFileLines;
        }

        public List<String> getCorruptedLines() {
            return corruptedLines;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
