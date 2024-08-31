package TrackBot;

import TrackBot.task.Task;
import TrackBot.ui.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles file storage for TrackBot.
 * This class manages reading and writing tasks to a file, and ensures the
 * file and directories are created when needed.
 */
public class TrackBotStorage {
    String filePath;
    File file;

    /**
     * Constructs a TrackBotStorage object for a specified file path.
     * Ensures that the necessary file and directories are created.
     *
     * @param filePath The file path where tasks are stored.
     * @throws IOException If an I/O error occurs while creating the file or directories.
     */
    public TrackBotStorage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        createNewFile();
    }

    /**
     * Creates a new file and its parent directories if they do not exist.
     *
     * @throws IOException If an I/O error occurs while creating the file or directories.
     */
    public void createNewFile() throws IOException {
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
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
        List<Task> list = new ArrayList<>();

        if (!file.exists()) {
            System.out.println("Data file does not exist. A new file will be created.");
            return list;
        }

        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            Task task = Parser.parseTask(line);
            if (task != null) {
                list.add(task);
            }
        }
        s.close();
        return list;
    }
    /**
     * Saves the tasks from a list into the storage file.
     *
     * @param list The list of tasks to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveContents(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task task : list) {
            fw.write(task.toStorageFormat() + System.lineSeparator());
        }
        fw.close();
    }
}
