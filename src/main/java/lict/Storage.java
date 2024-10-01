package lict;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import lict.task.Task;


/**
 * The {@code Storage} class handles the reading and writing of task data to and from a file.
 * It manages loading tasks from a file into memory and saving tasks from memory back to the file.
 */
public class Storage {
    private File file;

    /**
     * Constructs a {@code Storage} object that handles the file operations at the specified path.
     *
     * @param path The file path where task data will be stored.
     */
    public Storage(String path) {
        this.file = new File(path);
    }

    /**
     * Loads tasks from the file into an {@code ArrayList} of {@code Task} objects.
     * If the file or directory does not exist, it creates them.
     *
     * @return An {@code ArrayList} of tasks loaded from the file.
     * @throws LictException If an I/O error occurs while loading tasks.
     */
    public ArrayList<Task> loadTasks() throws LictException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String dataEntry = sc.nextLine();
                    Task task = Task.convertData(dataEntry); // lict.task.Task class handles parsing
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                sc.close();
            } else {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            }
            return tasks;
        } catch (IOException e) {
            throw new LictException(
                    "An error occurred while creating the data directory or the LictData.txt file: "
                            + e.getMessage());
        }
    }

    /**
     * Saves the tasks in the provided {@code TaskList} to the file.
     *
     * @param taskList The {@code TaskList} containing tasks to be saved.
     * @throws LictException If an I/O error occurs while writing tasks to the file.
     */
    public void saveTasks(TaskList taskList) throws LictException {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList.getTasks()) {
                writer.write(task.toData());
            }
            writer.close();
        } catch (IOException e) {
            throw new LictException("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}

