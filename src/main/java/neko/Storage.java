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

    /** The directory path where the file will be stored. */
    private static final String DIRECTORY_PATH = "./data";

    /** The file path where task data is stored. */
    private final String filePath;

    /**
     * Constructs a Storage object to handle saving and loading of task data.
     *
     * @param filePath The file path where task data will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
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
     * Writes a string of text to the file. If the file or directory does not exist,
     * it creates them. Appends the text to the end of the file.
     *
     * @param text The text to be written to the file.
     * @throws IOException if an I/O error occurs.
     */
    public void writeFile(String text) throws IOException {
        // Check if the directory exists, else create a new one
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Check if the file exists, else create a new one
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        // Write to the file, appending the text
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(text);
        fw.close();
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
        FileWriter fw = new FileWriter(filePath, false);

        // Loop through the list of tasks and write each task to the file
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            String taskType = task.getClass().getSimpleName().charAt(0) + ""; // First letter of the task type
            String status = task.isDone() ? "1" : "0"; // 1 if done, 0 if not done
            String taskDetails = "";

            // Check the task type to specify the string representation of the task
            if (task instanceof Todo) {
                taskDetails = task.getDescription();
            } else if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                taskDetails = task.getDescription() + " | " + deadlineTask.getTime();
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                taskDetails = task.getDescription() + " | " + eventTask.getTime();
            }

            // Write task information to the file
            fw.write(taskType + " | " + status + " | " + taskDetails + "\n");
        }
        fw.close();
    }
}
