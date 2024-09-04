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
import java.util.Arrays;
import java.util.List;

import fishman.exception.FishmanException;
import fishman.exception.FishmanException.InvalidArgumentsException.ErrorType;
import fishman.task.Deadline;
import fishman.task.Event;
import fishman.task.Task;
import fishman.task.TaskList;
import fishman.task.ToDo;
import javafx.util.Pair;


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
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Saves the list of tasks to the save file as specified by the filepath. The tasks are converted to
     * CSV string format before being written to the file.
     *
     * @param tasks The list of tasks to be written to the save file.
     * @throws RuntimeException If an error occurs while writing to the file.
     */
    public void save(TaskList tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(toCsv(task));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error saving tasks to file: " + e.getMessage(), e);
        }
    }

    /**
     * Converts a Task object to a CSV string representation, with "|" as the delimiter.
     *
     * @param task The task to be converted to CSV format.
     * @return A string representing the task in CSV format.
     */
    private String toCsv(Task task) {
        StringBuilder sb = new StringBuilder();
        if (task instanceof ToDo) {
            sb.append("T").append(task.getStatus() ? "|true" : "|false").append("|").append(task.getDescription());
        } else if (task instanceof Deadline) {
            sb.append("D").append(task.getStatus() ? "|true" : "|false").append("|").append(task.getDescription()).append("|")
                            .append(((Deadline) task).getBy().format(DATE_TIME_FORMATTER));
        } else if (task instanceof Event) {
            sb.append("E").append(task.getStatus() ? "|true" : "|false").append("|").append(task.getDescription()).append("|")
                            .append(((Event) task).getFrom().format(DATE_TIME_FORMATTER)).append("|")
                            .append(((Event) task).getTo().format(DATE_TIME_FORMATTER));
        }
        return sb.toString();
    }

    /**
     * Loads the tasks from the file specified by the filepath. Each line is read and parsed into the
     * corresponding Task object.
     *
     * @return A TaskList object containing all the loaded tasks.
     * @throws FishmanException.InvalidArgumentsException If the file contains lines with invalid arguments.
     * @throws RuntimeException If an error occurs while reading the file.
     */
    public Pair<TaskList, String> load() throws FishmanException {
        TaskList tasks = new TaskList();
        errorMessages.clear();

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                try {
                    String[] arguments = line.split("\\|", -1);
                    if (arguments.length < 3) {
                        throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_ARGUMENTS, line);
                    }
                    String type = arguments[0].trim();
                    String isDoneStr = arguments[1].trim();
                    String description = arguments[2].trim();

                    boolean isDone;
                    if (isDoneStr.equalsIgnoreCase("true")) {
                        isDone = true;
                    } else if (isDoneStr.equalsIgnoreCase("false")) {
                        isDone = false;
                    } else {
                        throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_IS_DONE, line);
                    }

                    switch (type) {
                    case "T":
                        if (arguments.length != 3 || description.isEmpty()) {
                            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_TODO, line);
                        }
                        tasks.addTask(new ToDo(description, isDone));
                        break;
                    case "D":
                        if (arguments.length != 4 || description.isEmpty()) {
                            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_DEADLINE, line);
                        }
                        String deadline = arguments[3].trim();
                        if (deadline.isEmpty()) {
                            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_EVENT, line);
                        }
                        LocalDateTime deadlineDate = parseDateTime(deadline, line);
                        tasks.addTask(new Deadline(description, isDone, deadlineDate));
                        break;
                    case "E":
                        if (arguments.length != 5 || description.isEmpty()) {
                            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_EVENT,line);
                        }
                        String from = arguments[3].trim();
                        String to = arguments[4].trim();
                        LocalDateTime fromDate = parseDateTime(from, line);
                        LocalDateTime toDate = parseDateTime(to, line);
                        tasks.addTask(new Event(description, isDone, fromDate, toDate));
                        break;
                    default:
                        throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_TASK_TYPE, line);
                    }
                } catch (FishmanException e) {
                    errorMessages.add(e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }

        String combinedErrorMessage = errorMessages.isEmpty() ? null :
                String.join("\n", errorMessages) + "\nInvalid data lines will be deleted on exit. Please re-enter the tasks in the correct format!";

        return new Pair<>(tasks, combinedErrorMessage);
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
        try {
            return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new FishmanException.InvalidArgumentsException(ErrorType.INVALID_DATE_FORMAT, line);
        }
    }
}
