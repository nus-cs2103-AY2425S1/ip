package justbot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import justbot.command.CommandType;
import justbot.exception.JustbotException;
import justbot.task.Deadline;
import justbot.task.Event;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.task.Todo;

/**
 * Handles the loading and saving of tasks to and from a file.
 * The Storage class is responsible for creating the necessary file if it doesn't exist,
 * saving tasks to the file, and loading tasks from the file.
 */
public class Storage {
    private String filePath;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates the file and its parent directories if they do not exist.
     * If the file already exists, this method does nothing.
     */
    public void createFileIfDoesNotExist() {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Hey man, something went wrong when I tried creating the file or directory.");
        }
    }

    /**
     * Writes text to the file specified by the file path.
     * This method is private and used internally to write to the file.
     *
     * @param filePath The path of the file to write to.
     * @param textToAdd The text to be added to the file.
     * @param append If true, the text is added to the end of the file; if false, the file is overwritten.
     * @throws IOException If an I/O error occurs.
     */
    private static void writeToFile(String filePath, String textToAdd, boolean append) throws IOException {
        FileWriter fw = new FileWriter(filePath, append);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves the tasks in the TaskList to the file.
     * The tasks are saved in a specific format so they can be reloaded later.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void saveTasks(TaskList taskList) {
        try {
            writeToFile(filePath, "", false);
            for (Task task : taskList) {
                String taskString;
                // String format will be Type | isDone | Description | Time Constraint
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    taskString = "Deadline | " + deadline.getIsDoneString() + " | "
                            + deadline.getTaskDescription() + " | "
                            + deadline.getDeadline().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    taskString = "Event | " + event.getIsDoneString() + " | "
                            + event.getTaskDescription() + " | "
                            + "from " + event.getStart().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                            + " to " + event.getEnd().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                } else {
                    taskString = "Todo | "
                            + task.getIsDoneString()
                            + " | " + task.getTaskDescription()
                            + " | No time constraint";
                }
                writeToFile(filePath, taskString + "\n", true);
            }
        } catch (IOException e) {
            System.out.println("Hey man an error occured while saving tasks!");
        }
    }

    /**
     * Loads the tasks from the file and returns them as an ArrayList of Task objects.
     * The tasks are expected to be stored in a specific format.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws JustbotException If an error occurs while loading tasks.
     */
    public ArrayList<Task> loadTasks() throws JustbotException {
        ArrayList<Task> tasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().toLowerCase();
                try {
                    Task task = parseTask(line);
                    tasks.add(task);
                } catch (Exception e) {
                    System.out.println("Skipping malformed task due to error: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    private Task parseTask(String line) throws Exception {
        String[] parts = line.split("\\|");

        if (parts.length < 4) {
            throw new Exception("Malformed line: " + line);
        }

        CommandType type = CommandType.fromString(parts[0].trim());
        String isDoneString = parts[1].trim();
        String description = parts[2].trim();
        String timeConstraint = parts[3].trim();

        Task task;
        switch (type) {
        case TODO:
            task = parseTodoTask(description);
            break;
        case DEADLINE:
            task = parseDeadlineTask(description, timeConstraint);
            break;
        case EVENT:
            task = parseEventTask(description, timeConstraint);
            break;
        default:
            throw new Exception("Unknown task type: " + type);
        }

        if (isDoneString.equals("x")) {
            task.setIsDone(true);
        }

        return task;
    }

    private Todo parseTodoTask(String description) {
        return new Todo(description);
    }

    private Deadline parseDeadlineTask(String description, String timeConstraint) throws DateTimeParseException {
        String deadlineDateTimeString = timeConstraint.replace("by:", "").trim();
        LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineDateTimeString, formatter);
        return new Deadline(description, deadlineDateTime);
    }

    private Event parseEventTask(String description, String timeConstraint) throws DateTimeParseException {
        String[] timeParts = timeConstraint.split(" to ");
        if (timeParts.length < 2) {
            throw new IllegalArgumentException("Event time constraint is malformed: " + timeConstraint);
        }
        LocalDateTime start = LocalDateTime.parse(timeParts[0].replace("from ", "").trim(), formatter);
        LocalDateTime end = LocalDateTime.parse(timeParts[1].trim(), formatter);
        return new Event(description, start, end);
    }

}
