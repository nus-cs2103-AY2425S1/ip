package waterfall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import waterfall.task.Deadline;
import waterfall.task.Event;
import waterfall.task.Task;
import waterfall.task.ToDo;

/**
 * Manages data storage of users' tasks. A <code>Storage</code> object corresponds to
 * a direct control to the file specified by the users' filePath.
 * It provides functionalities to read tasks from the file, add tasks to the file,
 * and update the existing tasks in the file.
 *
 * @author Wai Hong
 */

public class Storage {
    private final File taskFile;

    /**
     * Creates a new <code>Storage</code> instance for managing tasks in the specified file.
     * Auto creates the file if the file or its parent directory does not exist.
     *
     * @param filePath The path to the file where tasks are stored.
     * @throws IOException If I/O exception occurs when creating the file or directory.
     */
    public Storage(String filePath) throws IOException {
        assert filePath != null : "filePath cannot be null";
        taskFile = new File(filePath);
        if (!taskFile.getParentFile().exists()) {
            taskFile.getParentFile().mkdirs();
        }
        if (!taskFile.exists()) {
            taskFile.createNewFile();
        }
    }

    /**
     * Add a new task to the storage file. The task is appended to the end of the file.
     *
     * @param task The task to be added.
     * @throws IOException If I/O exception occurs while writing to the file.
     */
    public void addTask(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(taskFile, true);
        fileWriter.write(task.toStorageString() + System.lineSeparator());
        fileWriter.close();
    }

    /**
     * Updates the storage file with the provided list of tasks.
     * This method overwrites the entire file content with the tasks provided.
     *
     * @param tasks List of tasks to be stored in the storage file.
     * @throws IOException If I/O exception occurs while writing to the file.
     */
    public void updateTask(List<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(taskFile);
        for (Task task : tasks) {
            fileWriter.write(task.toStorageString() + System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Loads tasks from the storage file and stores them in a list.
     * Each task is parsed based on the format stored in the file and added to the list.
     *
     * @return A list of tasks loaded from the storage file.
     * @throws WaterfallException If the file is not found or there exists an unknown task data.
     */
    public List<Task> load() throws WaterfallException {
        try {
            Scanner scanner = new Scanner(taskFile);
            List<Task> tasks = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                Task task = parseAndGetTask(line);
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new WaterfallException("File not found: " + taskFile);
        }
    }

    private static Task parseAndGetTask(String line) throws WaterfallException {
        String[] strings = line.split(" \\| ");
        Task task;
        switch (strings[0]) {
        case "T":
            assert strings.length == 3 : "Todo should have 3 arguments";
            task = new ToDo(strings[2]);
            break;
        case "D":
            assert strings.length == 4 : "Deadline should have 4 arguments";
            task = new Deadline(strings[2], strings[3]);
            break;
        case "E":
            assert strings.length == 5 : "Event should have 5 arguments";
            task = new Event(strings[2], strings[3], strings[4]);
            break;
        default:
            throw new WaterfallException("Unknown task type in database: " + line);
        }
        if (Objects.equals(strings[1], "1")) {
            task.setDone(true);
        }
        return task;
    }
}
