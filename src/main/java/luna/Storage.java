package luna;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import luna.task.Deadline;
import luna.task.Event;
import luna.task.Task;
import luna.task.Todo;

/**
 * Handles the storage of tasks.
 * This class is responsible for saving and loading tasks from a file.
 */
public class Storage {
    public static final String FILE_PATH = "luna.txt";
    public static final String TEMP_FILE_PATH = "temp.txt";

    /**
     * Loads tasks from a specified file.
     *
     * @return A list of tasks loaded from the file.
     * @throws LunaException If an error occurs while reading the file.
     */
    public ArrayList<Task> loadTasks() throws LunaException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);

            if (file.createNewFile()) {
                return tasks;
            }

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] taskInputs = task.split("\\|");
                String taskType = taskInputs[0];
                int taskStatus = Integer.parseInt(taskInputs[1]);
                assert (taskStatus == 0 || taskStatus == 1);

                switch (taskType) {
                case "T":
                    Todo todo = new Todo(taskInputs[2]);
                    if (taskStatus == 1) {
                        todo.markAsDone();
                    }
                    tasks.add(todo);
                    break;

                case "D":
                    Deadline deadline = new Deadline(taskInputs[2], LocalDateTime.parse(taskInputs[3]));
                    if (taskStatus == 1) {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                    break;

                case "E":
                    Event event = new Event(taskInputs[2], LocalDateTime.parse(taskInputs[3]),
                            LocalDateTime.parse(taskInputs[4]));
                    if (taskStatus == 1) {
                        event.markAsDone();
                    }
                    tasks.add(event);
                    break;

                default:
                }
            }
        } catch (FileNotFoundException e) {
            throw new LunaException("File not found. Create a new text file luna.txt in the data directory");
        } catch (Exception e) {
            throw new LunaException("Data file is corrupted or not in expected format");
        }
        return tasks;
    }

    /**
     * Saves tasks to the specified file.
     *
     * @param tasks List of task to be saved to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            File tempFile = new File(TEMP_FILE_PATH);
            FileWriter fw = new FileWriter(tempFile);

            for (Task task : tasks) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }

            fw.close();
            Path tempPath = Paths.get(TEMP_FILE_PATH);
            Files.copy(tempPath, Paths.get(FILE_PATH), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(tempPath);

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
