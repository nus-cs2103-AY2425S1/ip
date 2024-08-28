package fishman.utils;

import fishman.exception.FishmanException;
import fishman.task.Deadline;
import fishman.task.Event;
import fishman.task.Task;
import fishman.task.TaskList;
import fishman.task.ToDo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The storage class is used to handle the storage of tasks to and from a save file.
 */
public class Storage {

    private final Path filePath;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

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
     * Saves the list of tasks to the save file as specified by the filepath. The tasks are converted to CSV string format
     * before being written to the file.
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
        if(task instanceof ToDo) {
            sb.append("T").append(task.getStatus() ? "|1" : "|0").append("|").append(task.getDescription());
        } else if(task instanceof Deadline) {
            sb.append("D").append(task.getStatus() ? "|1" : "|0").append("|").append(task.getDescription()).append("|")
                            .append(((Deadline) task).getBy().format(DATE_TIME_FORMATTER));
        } else if(task instanceof Event) {
            sb.append("E").append(task.getStatus() ? "|1" : "|0").append("|").append(task.getDescription()).append("|")
                            .append(((Event) task).getFrom().format(DATE_TIME_FORMATTER)).append("|")
                            .append(((Event) task).getTo().format(DATE_TIME_FORMATTER));
        }
        return sb.toString();
    }

    /**
     * Loads the tasks from the file specified by the filepath. Each line is read and parsed into the corresponding Task object.
     *
     * @return A TaskList object containing all the loaded tasks.
     * @throws FishmanException.InvalidArgumentsException If the file contains lines with invalid arguments.
     * @throws RuntimeException If an error occurs while reading the file.
     */
    public TaskList load() throws FishmanException {
        TaskList tasks = new TaskList();

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] arguments = line.split("\\|", -1);
                if (arguments.length < 3) {
                    throw new FishmanException.InvalidArgumentsException(line);
                }
                String type = arguments[0];
                boolean isDone = arguments[1].equals("1");
                String description = arguments[2];

                switch (type) {
                case "T":
                    if (arguments.length != 3) {
                        throw new FishmanException.InvalidArgumentsException(line);
                    }
                    tasks.addTask(new ToDo(description, isDone));
                    break;
                case "D":
                    if (arguments.length != 4) {
                        throw new FishmanException.InvalidArgumentsException(line);
                    }
                    String deadline = arguments[3];
                    LocalDateTime deadlineDate = parseDateTime(deadline);
                    tasks.addTask(new Deadline(description, isDone, deadlineDate));
                    break;
                case "E":
                    if (arguments.length != 5) {
                        throw new FishmanException.InvalidArgumentsException(line);
                    }
                    String from = arguments[3];
                    String to = arguments[4];
                    LocalDateTime fromDate = parseDateTime(from);
                    LocalDateTime toDate = parseDateTime(to);
                    tasks.addTask(new Event(description, isDone, fromDate, toDate));
                    break;
                default:
                    throw new FishmanException.InvalidArgumentsException("Empty line or unknown task type in line: "
                                    + "<" + line + ">");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
        return tasks;
    }

    /**
     * Parses a date-time string into a LocalDateTime object using the specified date-time formatter.
     * This method attempts to parse the given date-time string using the DATE_TIME_FORMATTER.
     * If the string does not conform to the expected format, a FishmanException is thrown to indicate parsing error.
     *
     * @param dateTimeStr The date-time string to parses.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws FishmanException.InvalidArgumentsException If the input string does not match the expected date-time format.
     */
    private static LocalDateTime parseDateTime(String dateTimeStr) throws FishmanException {
        try {
            return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new FishmanException.InvalidDateFormatException(dateTimeStr);
        }
    }

}