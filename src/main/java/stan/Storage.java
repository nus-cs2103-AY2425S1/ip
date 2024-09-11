package stan;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import stan.exceptions.StanInvalidDateTimeFormatException;
import stan.tasks.Deadline;
import stan.tasks.Event;
import stan.tasks.Task;
import stan.tasks.Todo;




/**
 * This class represents the storage to read and save tasks from the hard disk.
 */
public class Storage {

    private static final String DIR_PATH = "data/";
    private static final String FILE_PATH = DIR_PATH + "stan.txt";
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads tasks from the file, or creates the file if it doesn't exist.
     *
     * @return A list of all the tasks stored in the file.
     */
    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("The file to store all your tasks does not exist.");
            try {
                createFile(DIR_PATH, FILE_PATH);
                System.out.println("A new file has been created at " + FILE_PATH);
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
            }
            return tasks;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] taskDetails = task.split(" \\| ");
                try {
                    Task parsedTask = parseTask(taskDetails);
                    if (parsedTask != null) {
                        tasks.add(parsedTask);
                    }
                } catch (StanInvalidDateTimeFormatException e) {
                    System.out.println("Error loading task: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + FILE_PATH);
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(List<Task> tasks) {
        String tempFilePath = FILE_PATH + ".tmp";
        try (FileWriter fw = new FileWriter(createFile(DIR_PATH, tempFilePath))) {
            for (Task task : tasks) {
                fw.write(task.toStorageString() + System.lineSeparator());
            }
            Files.move(Paths.get(tempFilePath), Paths.get(FILE_PATH), REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file.");
        }
    }

    /**
     * Creates a directory and file if they do not exist.
     *
     * @param dirPath  The directory path.
     * @param filePath The file path.
     * @return The file created.
     * @throws IOException If an I/O error occurs.
     */
    private static File createFile(String dirPath, String filePath) throws IOException {
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * Parses a task from the file format.
     *
     * @param taskDetails The array of task details.
     * @return The task object.
     */
    private static Task parseTask(String[] taskDetails) throws StanInvalidDateTimeFormatException {
        Task task = null;
        String taskType = taskDetails[0];
        boolean isDone = taskDetails[1].equals("1");
        String description = taskDetails[2];

        switch (taskType) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            task = new Deadline(description, taskDetails[3]);
            break;
        case "E":
            task = new Event(description, taskDetails[3], taskDetails[4]);
            break;
        default:
            System.out.println("Unknown task type: " + taskType);
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }
}
