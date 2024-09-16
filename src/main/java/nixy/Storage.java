package nixy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import nixy.exceptions.NixyException;
import nixy.task.ReadableTask;
import nixy.task.Task;
import nixy.task.TaskDecoder;
import nixy.task.TaskList;

/**
 * Storage class is responsible for loading and saving tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath The file path to store tasks data.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path should not be null";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from file into a list.
     * List of task meant to be used to create a TaskList object.
     * If file does not exist, an empty list is returned.
     *
     * @return The list of tasks loaded from file.
     * @throws NixyException If there is an error loading tasks from file.
     */
    public List<Task> load() throws NixyException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                Task task = TaskDecoder.parseTask(taskString);
                assert task != null : "Parsed task should not be null";
                tasks.add(task);
            }
            scanner.close();
        } catch (IOException e) {
            throw new NixyException("BLAHH!!! Error loading tasks from file.");
        }
        return tasks;
    }

    /**
     * Saves tasks to file.
     * Overwrites the file if it already exists.
     *
     * @param taskList The task list to save.
     * @throws NixyException If there is an error saving tasks to file.
     */
    public void save(TaskList taskList) throws NixyException {
        try {
            File file = new File(filePath);

            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(filePath);
            Iterator<ReadableTask> taskIterator = taskList.getTasksIterator();
            for (int i = 0; taskIterator.hasNext(); i++) {
                ReadableTask task = taskIterator.next();
                assert task != null : "Task should not be null";
                fileWriter.write(String.format("%s%n", task.toDataString()));
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new NixyException("BLAHH!!! Error saving tasks to file.");
        }
    }
}
