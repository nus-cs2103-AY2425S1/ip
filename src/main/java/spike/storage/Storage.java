package spike.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import spike.exceptions.SpikeException;
import spike.tasks.Deadline;
import spike.tasks.Event;
import spike.tasks.Task;
import spike.tasks.ToDo;

/**
 * Represents a storage object that handles reading and writing to a file.
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructor for a storage object.
     * Creates a new file if it does not exist.
     *
     * @param filePath The file path to read and write tasks to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath);
    }

    /**
     * Writes the tasks to the file.
     *
     * @param tasks The list of tasks to write to the file.
     * @throws SpikeException If an error occurs while writing to the file.
     */
    public void writeToFile(TaskList tasks) throws SpikeException {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks.getAllTasks()) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new SpikeException("An error occurred while writing to file");
        }
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     * Creates a new file and returns an empty ArrayList if the file does not exist.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws SpikeException If an error occurs while reading from the file.
     */
    public ArrayList<Task> loadFromFile() throws SpikeException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return loadedTasks;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                case "T":
                    task = new ToDo(parts[2]);
                    break;
                case "D":
                    LocalDateTime deadline = LocalDateTime.parse(parts[3]);
                    task = new Deadline(parts[2], deadline);
                    break;
                case "E":
                    LocalDateTime from = LocalDateTime.parse(parts[3]);
                    LocalDateTime to = LocalDateTime.parse(parts[4]);
                    task = new Event(parts[2], from, to);
                    break;
                default:
                    throw new SpikeException("An error occurred while reading from file");
                }
                if (parts[1].equals("1")) {
                    task.markAsDone();
                }
                loadedTasks.add(task);
            }
            scanner.close();
        } catch (IOException e) {
            throw new SpikeException("An error occurred while reading from file");
        }
        return loadedTasks;
    }

    /**
     * Clears the file.
     *
     * @throws SpikeException If an error occurs while clearing the file.
     */
    public void clearFile() throws SpikeException {
        try {
            FileWriter writer = new FileWriter(this.file, false);
            writer.close();
        } catch (IOException e) {
            throw new SpikeException("An error occurred while clearing the file");
        }
    }

}
