package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Handles loading and saving tasks from and to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file used to store tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * If the file does not exist, it creates the necessary directories and an empty file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an error occurs while reading from or creating the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // If the file doesn't exist, create the directory and the file
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Create the directory if it does not exist
            file.createNewFile(); // Create the file if it does not exist
            return tasks; // Return an empty list if no file exists
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            if (taskType.equals("T")) {
                task = new ToDo(description);
            } else if (taskType.equals("D")) {
                // Parse the date stored in the file into a LocalDate object
                LocalDate by = LocalDate.parse(parts[3]);
                task = new Deadline(description, by);
            } else if (taskType.equals("E")) {
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
            } else {
                continue;
            }

            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the file specified by the file path.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.toSaveFormat() + System.lineSeparator());
        }
        writer.close();
    }
}