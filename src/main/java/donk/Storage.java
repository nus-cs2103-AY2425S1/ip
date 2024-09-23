package donk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import donk.task.Deadline;
import donk.task.Event;
import donk.task.Task;
import donk.task.TaskList;
import donk.task.ToDo;

/**
 * Contains filepath, and handles IO operations with files
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for the Storage class.
     * Initializes the storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return A list of tasks loaded from the file.
     * @throws DonkException If the file cannot be found or an error occurs while loading the tasks.
     */
    public List<Task> load() throws DonkException {
        try {
            List<Task> tasks = readFile();
            return tasks;
        } catch (Exception e) {
            throw new DonkException("Couldn't find file");
        }
    }

    /**
     * Reads tasks from the file specified by the file path.
     *
     * @return A list of tasks read from the file.
     * @throws FileNotFoundException If the file specified by the file path cannot be found.
     */
    private List<Task> readFile() throws FileNotFoundException {
        File f = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        List<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] data = line.split("\\|");
            if (line.charAt(0) == 'T') {
                tasks.add(new ToDo(data[2]));
            } else if (line.charAt(0) == 'D') {
                tasks.add(new Deadline(data[2], data[3]));
            } else if (line.charAt(0) == 'E') {
                tasks.add(new Event(data[2], data[3], data[4]));
            }
            if (line.charAt(2) == '1') {
                tasks.get(tasks.size() - 1).markDone();
            }
        }
        return tasks;
    }

    /**
     * Writes the list of tasks to the file specified by the file path.
     *
     * @param filePath The path to the file where tasks will be saved.
     * @param tasks The TaskList object containing the tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public static void writeToFile(String filePath, TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.getTask(i).toFileSaveString() + "\n");
        }

        fw.close();
    }


}
