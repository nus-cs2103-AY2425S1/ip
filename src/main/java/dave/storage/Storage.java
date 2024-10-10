package dave.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import dave.exceptions.InvalidDateTimeFormatException;
import dave.exceptions.InvalidDescriptionException;
import dave.task.Deadline;
import dave.task.Event;
import dave.task.Task;
import dave.task.TaskList;
import dave.task.Todo;

/**
 * The Storage class handles the loading and saving of tasks to and from a file.
 */
public class Storage {
    private static String filePath;

    /**
     * Constructs a Storage object and loads tasks from the specified file into the given TaskList.
     *
     * @param filePath The file path to load tasks from and save tasks to.
     * @param taskList The TaskList to populate with tasks loaded from the file.
     */
    public Storage(String filePath, TaskList taskList) {
        assert filePath != null && !filePath.isEmpty() : "File path must not be null or empty";
        Storage.filePath = filePath;
        createFileIfNeeded(); // Ensure the file is created only if it doesn't exist
        loadTasks(taskList);
    }

    /**
     * Loads tasks from the file and populates the TaskList.
     *
     * @param taskList The TaskList to populate with tasks.
     */
    private void loadTasks(TaskList taskList) {
        try {
            taskList.setTaskList(loadFile());
        } catch (IOException e) {
            System.out.println("Error occurred while loading tasks: " + e.getMessage());
        }
    }

    /**
     * Saves the given TaskList to the file.
     *
     * @param dataList The TaskList containing tasks to be saved.
     */
    public static void saveFile(TaskList dataList) {
        File fileObj = createFileIfNeeded();
        try {
            writeTasksToFile(dataList, fileObj);
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Creates the file if it doesn't already exist. If the file exists, it does nothing.
     *
     * @return The File object for the file path.
     */
    private static File createFileIfNeeded() {
        File fileObj = new File(filePath);
        try {
            fileObj.getParentFile().mkdirs(); // Ensure parent directories exist
            if (!fileObj.exists()) {
                fileObj.createNewFile(); // Create the file if it doesn't exist
            }
        } catch (IOException e) {
            System.out.println("Error occurred while creating file: " + e.getMessage());
        }
        return fileObj;
    }

    /**
     * Writes tasks from the TaskList to the specified file.
     *
     * @param dataList The TaskList containing tasks to be written to the file.
     * @param fileObj  The File object representing the file to be written to.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private static void writeTasksToFile(TaskList dataList, File fileObj) throws IOException {
        try (FileWriter fw = new FileWriter(fileObj)) {
            for (int i = 0; i < dataList.getSize(); i++) {
                fw.write(dataList.getTask(i).write());
            }
        }
    }

    /**
     * Clears the contents of the file.
     */
    public static void clearFile() {
        writeToFile("");
    }

    /**
     * Writes content to the file, overwriting the existing contents.
     *
     * @param content The content to be written to the file.
     */
    private static void writeToFile(String content) {
        try (FileWriter fw = new FileWriter(filePath, false)) {
            fw.write(content);
        } catch (IOException e) {
            System.out.println("Error occurred while clearing file: " + e.getMessage());
        }
    }

    /**
     * Appends a task to the file.
     *
     * @param task The Task to be appended to the file.
     */
    public static void amendFile(Task task) {
        File fileObj = createFileIfNeeded();
        try (FileWriter fw = new FileWriter(fileObj, true)) {
            fw.write(task.write());
        } catch (IOException e) {
            System.out.println("Error occurred while appending task: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading from the file.
     */
    public ArrayList<Task> loadFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a task from a line of text from the file.
     *
     * @param line The line of text representing a task.
     * @return The parsed Task.
     */
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        try {
            return createTask(taskType, isDone, description, parts);
        } catch (InvalidDateTimeFormatException | InvalidDescriptionException e) {
            System.out.println("Error occurred while loading task: " + e.getMessage());
            return null;
        }
    }

    /**
     * Creates a task based on the task type and parsed data.
     *
     * @param taskType    The type of the task (T, D, or E).
     * @param isDone      Whether the task is marked as done.
     * @param description The task description.
     * @param parts       Additional parts of the task for deadlines or events.
     * @return The created Task.
     */
    private Task createTask(String taskType, boolean isDone, String description, String[] parts)
            throws InvalidDescriptionException, InvalidDateTimeFormatException {
        switch (taskType) {
        case "T":
            return createTodoTask(description, isDone);
        case "D":
            return createDeadlineTask(description, isDone, parts[3]);
        case "E":
            return createEventTask(description, isDone, parts[3]);
        default:
            System.out.println("Unrecognized task type: " + taskType);
            return null;
        }
    }

    private Todo createTodoTask(String description, boolean isDone) {
        Todo todo = new Todo(description);
        if (isDone) {
            todo.markAsDone();
        }
        return todo;
    }

    private Deadline createDeadlineTask(String description, boolean isDone, String datePart)
            throws InvalidDateTimeFormatException, InvalidDescriptionException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        LocalDateTime dueDate = LocalDateTime.parse(datePart.trim(), formatter);
        Deadline deadline = new Deadline(description + " /by "
                + dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        if (isDone) {
            deadline.markAsDone();
        }
        return deadline;
    }

    private Event createEventTask(String description, boolean isDone, String eventPart)
            throws InvalidDateTimeFormatException, InvalidDescriptionException {
        String[] eventParts = eventPart.split(" - ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        LocalDateTime fromDate = LocalDateTime.parse(eventParts[0], formatter);
        Event event = new Event(description + " /from "
                + fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " /to " + eventParts[1]);
        if (isDone) {
            event.markAsDone();
        }
        return event;
    }
}
