package bobby.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import bobby.exceptions.InvalidTaskException;
import bobby.tasklist.TaskList;
import bobby.tasks.Deadline;
import bobby.tasks.Event;
import bobby.tasks.Task;
import bobby.tasks.Todo;


/**
 * The {@code Storage} class manages reading and writing tasks to and from a file.
 * It ensures that the tasks in a {@code TaskList} can be saved persistently and
 * loaded back when the program is restarted.
 * <p>
 * Tasks are saved in a text format, with each line representing a task's type, status,
 * description, and any relevant dates (such as deadlines or event times).
 * </p>
 * <p>
 * If the specified file does not exist, it is created automatically.
 * </p>
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} object that reads from and writes to the specified file path.
     *
     * @param filePath the path of the file where tasks are stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the tasks to the file using the provided FileWriter.
     *
     * @param writer the {@code FileWriter} object used to write the tasks
     * @param tasks  the {@code TaskList} containing the tasks to be written
     */
    private void writeTasksToFile(FileWriter writer, TaskList tasks) {
        tasks.getTasks().forEach(task -> {
            try {
                writer.write(task.toFileString() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
                e.printStackTrace();
            }
        });
        System.out.println("Tasks successfully saved to file.");
    }
    /**
     * Saves the list of tasks to a file.
     *
     * @param tasks the {@code TaskList} containing the tasks to be saved
     */
    public void saveTasks(TaskList tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writeTasksToFile(writer, tasks);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Creates a new file and its parent directories if they do not exist.
     *
     * @param file the file to create
     */
    private void createFile(File file) {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the file into a {@code Task} object.
     * The line is split into its components, and a task is created based on its type and details.
     * If the task cannot be parsed, an {@code InvalidTaskException} is thrown.
     *
     * @param line the string representing the task in the file
     * @return the corresponding {@code Task} object
     * @throws InvalidTaskException if the task cannot be parsed
     */
    private Task parseTask(String line) throws InvalidTaskException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("true");
        String description = parts[2];

        Task task = createTask(taskType, parts, description);
        if (isDone) {
            task.markTask();
        }
        return task;
    }

    /**
     * Creates a {@code Task} object based on the provided type and details.
     * If the type corresponds to a {@code Todo}, {@code Deadline}, or {@code Event}, the task is created
     * accordingly with the correct information.
     *
     * @param taskType    the type of the task (T, D, E)
     * @param parts       the components of the task string from the file
     * @param description the description of the task
     * @return the created {@code Task} object
     * @throws InvalidTaskException if the task type is invalid
     */
    private Task createTask(String taskType, String[] parts, String description) throws InvalidTaskException {
        switch (taskType) {
        case "T":
            return new Todo(description);
        case "D":
            LocalDate by = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return new Deadline(description, by);
        case "E":
            LocalDate from = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate to = LocalDate.parse(parts[4], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return new Event(description, from, to);
        default:
            throw new InvalidTaskException("Unable to create task. Please check your the file structure.");
        }
    }

    /**
     * Loads tasks from the file into a {@code TaskList}.
     * If the file does not exist, it is created, and an empty task list is returned.
     * If an error occurs during loading, an error message is displayed.
     *
     * @return a {@code TaskList} containing the loaded tasks, or an empty list if the file does not exist
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(filePath);
        if (!file.exists()) {
            createFile(file);
            return tasks;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tasks.add(parseTask(line));
            }
        } catch (IOException | InvalidTaskException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }
}
