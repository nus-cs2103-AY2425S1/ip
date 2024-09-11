package neko;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import neko.task.Deadline;
import neko.task.Event;
import neko.task.Task;
import neko.task.TaskList;
import neko.task.Todo;

/**
 * The Storage class handles reading and writing task data to and from a file.
 * It manages saving the state of the task list and loading it when the application is restarted.
 */
public class Storage {

    private static final String DEFAULT_FILE_PATH = "./data/neko.txt";
    private static final String DEFAULT_DIRECTORY_PATH = "./data/";

    /** The file path where task data is stored. */
    private String filePath;

    /**
     * Constructs a Storage object to handle saving and loading of task data.
     *
     * @param filePath The file path where task data will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage() {
        this.filePath = DEFAULT_FILE_PATH;
    }

    /**
     * Loads tasks from the file specified in {@code filePath}.
     * Each line in the file is parsed into a Task object using the {@code Parser}.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws FileNotFoundException if the file does not exist.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {

        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);

        if (f.exists()) {
            Scanner s = new Scanner(f);

            // Parse each line from the file into a Task object
            while (s.hasNext()) {
                String str = s.nextLine();
                Task task = Parser.parseTask(str);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    /**
     * Writes a single task to the file, appending it to the end.
     * Creates directories and files if they don't exist.
     *
     * @param task The task to be written to the file.
     * @throws IOException if an I/O error occurs.
     */
    public void writeFile(Task task) throws IOException {
        createDirectoryIfNotExist(DEFAULT_DIRECTORY_PATH);

        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(formatTask(task) + "\n");
        }
    }

    /**
     * Rewrites the file with the current list of tasks, replacing any existing content.
     * Each task is written in a specific format based on its type.
     *
     * @param tasks The TaskList object containing all tasks to be written to the file.
     * @throws IOException if an I/O error occurs during file writing.
     * @throws NekoException if an error occurs while processing tasks.
     */
    public void rewriteFile(TaskList tasks) throws IOException, NekoException {
        createDirectoryIfNotExist(DEFAULT_DIRECTORY_PATH);

        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                fileWriter.write(formatTask(task) + "\n");
            }
        }
    }

    /**
     * Ensures the directory exists. If it does not, it creates the directory.
     *
     * @param directoryPath The directory path to check or create.
     */
    private void createDirectoryIfNotExist(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Formats a task into a string suitable for file storage.
     *
     * @param task The task to format.
     * @return The formatted task string.
     */
    private String formatTask(Task task) {
        String taskType = task.getClass().getSimpleName().charAt(0) + ""; // First letter of the task type
        String status = task.isDone() ? "1" : "0"; // 1 if done, 0 if not done
        String taskDetails = "";

        if (task instanceof Todo) {
            taskDetails = task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            taskDetails = task.getDescription() + " | " + deadlineTask.getTime();
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            taskDetails = task.getDescription() + " | " + eventTask.getTime();
        }

        return taskType + " | " + status + " | " + taskDetails;
    }

}
