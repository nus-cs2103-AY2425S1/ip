package trackbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import trackbot.task.Task;
import trackbot.ui.Parser;

/**
 * Handles file storage for trackbot.
 * This class manages reading and writing tasks to a file, and ensures the
 * file and directories are created when needed.
 */
public class TrackBotStorage {
    private final String taskfilePath;
    private final File taskfile;

    /**
     * Constructs a TrackBotStorage object for a specified file path.
     * Ensures that the necessary file and directories are created.
     *
     * @param filePath The file path where tasks are stored.
     * @throws IOException If an I/O error occurs while creating the file or directories.
     */
    public TrackBotStorage(String filePath) throws IOException {
        this.taskfilePath = filePath;
        this.taskfile = new File(filePath);
        createNewFile();
    }

    /**
     * Creates a new file and its parent directories if they do not exist.
     *
     * @throws IOException If an I/O error occurs while creating the file or directories.
     */
    public void createNewFile() throws IOException {
        File directory = taskfile.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (!taskfile.exists()) {
            taskfile.createNewFile();
        }
    }

    /**
     * Loads the tasks from the storage file into a list.
     * If the file does not exist, an empty list is returned and a new file will be created.
     *
     * @return A list of tasks loaded from the file.
     * @throws FileNotFoundException If the file is not found.
     */
    public List<Task> loadContents() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();

        if (!taskfile.exists()) {
            System.out.println("Data file does not exist. A new file will be created.");
            return tasks;
        }
        assert taskfile.exists();
        assert taskfile.canRead();

        Scanner s = new Scanner(taskfile);
        while (s.hasNext()) {
            String line = s.nextLine();
            Task task = Parser.parseTask(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        s.close();
        return tasks;
    }
    /**
     * Saves the tasks from a list into the storage file.
     *
     * @param tasks The list of tasks to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveContents(List<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(this.taskfilePath);
        for (Task task : tasks) {
            fileWriter.write(task.toStorageFormat() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
