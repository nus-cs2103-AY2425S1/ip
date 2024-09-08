package noosy.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import noosy.exception.NoosyException;
import noosy.task.Deadline;
import noosy.task.Event;
import noosy.task.Task;
import noosy.task.TaskList;
import noosy.task.Todo;

/**
 * Handles the storage and retrieval of tasks from a file.
 */
public class Storage {

    /**
     * The file path where tasks are stored.
     */
    private String path;

    /**
     * The date-time formatter used for parsing and formatting event times.
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param path The file path where tasks will be stored and retrieved.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Adds a task to the storage file.
     *
     * @param task The task to be added to the file.
     * @throws NoosyException If there's an error writing to the file.
     */
    public void addTask(Task task) throws NoosyException {
        try {
            FileWriter fileWriter = new FileWriter(this.path, true);
            fileWriter.write(task.storeInFileAsString() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new NoosyException("Error writing file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws NoosyException If there's an error reading from the file.
     * @throws IOException If there's an I/O error or if the file contains an invalid task type.
     */
    public ArrayList<Task> load() throws NoosyException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File stored = new File(this.path);

        if (!stored.exists()) {
            stored.getParentFile().mkdirs();
            stored.createNewFile();
        } else {
            Scanner scanner = new Scanner(stored);
            while (scanner.hasNextLine()) {
                String taskInfo = scanner.nextLine();
                String[] separated = taskInfo.split(" \\| ");
                String taskType = separated[0];
                boolean status = separated[1].equals("1");
                String name = separated[2];

                switch (taskType) {
                    case "T":
                        tasks.add(new Todo(name, status));
                        break;

                    case "D":
                        LocalDate due = LocalDate.parse(separated[3]);
                        tasks.add(new Deadline(name, status, due));
                        break;

                    case "E":
                        String[] startAndEnd = separated[3].split("-");
                        LocalDateTime start = LocalDateTime.parse(startAndEnd[0].trim(), FORMATTER);
                        LocalDateTime end = LocalDateTime.parse(startAndEnd[1].trim(), FORMATTER);
                        tasks.add(new Event(name, status, start, end));
                        break;

                    default:
                        throw new IOException("Invalid task type in file: " + taskType);
                }
            }
            scanner.close();
        }

        return tasks;
    }

    /**
     * Saves the entire task list to the storage file.
     *
     * @param tasks The TaskList to be saved to the file.
     * @throws NoosyException If there's an error writing to the file.
     */
    public void save(TaskList tasks) throws NoosyException {
        try {
            FileWriter writer = new FileWriter(this.path);

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                writer.write(task.storeInFileAsString() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            throw new NoosyException("Cannot write file: " + e.getMessage());
        }
    }
}