package him;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import exceptions.HimException;

import task.Task;
import task.TaskList;

/**
 * Class to manage saving and loading Him data to disk.
 *
 * @author IsaacPangTH
 */
public class Storage {

    private static final String FILE_PATH = "data/tasks.txt";
    private static final String DIRECTORY_PATH = "data";

    /**
     * Initialises the storage.
     */
    public void initStorage() throws IOException {
        File path = new File(DIRECTORY_PATH);
        path.mkdir();
        File file = new File(FILE_PATH);
        file.createNewFile();
    }

    /**
     * Saves task list to disk as tasks.txt file.
     *
     * @param list Task list to be saved to storage
     * @throws IOException If and I/O error occurs
     */
    public void saveTaskList(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write(list.toData());
        fw.close();
    }

    /**
     * Loads task list from disk.
     *
     * @return Task list from disk
     * @throws FileNotFoundException If no tasks.txt file is found on disk
     * @throws HimException If invalid tasks.txt file is read
     */
    public TaskList loadTaskList() throws FileNotFoundException, HimException {
        File file = new File(FILE_PATH);
        TaskList list = new TaskList();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            if (data.trim().isEmpty()) {
                break;
            }
            list.add(Task.of(data));
        }
        return list;
    }
}
