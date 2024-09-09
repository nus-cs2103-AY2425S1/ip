package tick.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import tick.exceptions.TickException;
import tick.tasks.Deadline;
import tick.tasks.Event;
import tick.tasks.Task;
import tick.tasks.ToDo;

/**
 * Storage class handles the loading and saving of data to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage class.
     *
     * @param filePath The file path to save and load data from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the data to the file.
     *
     * @param taskList The list of tasks to save.
     * @throws TickException If an error occurs while saving data to file.
     */
    public void saveData(ArrayList<Task> taskList) throws TickException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (Task task : taskList) {
                fileWriter.write(task.toStorageFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new TickException("An error occurred while saving data to file.");
        }
    }

    /**
     * Loads the data from the file.
     *
     * @return The list of tasks loaded from the file.
     * @throws IOException If an error occurs while loading data from file.
     */
    public ArrayList<Task> loadData() throws IOException, TickException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(this.filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return taskList;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String taskString = scanner.nextLine();

            String[] parts = taskString.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            switch (taskType) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                LocalDate by = LocalDate.parse(parts[3]);
                task = new Deadline(description, by);
                break;
            case "E":
                LocalDate from = LocalDate.parse(parts[3]);
                LocalDate to = LocalDate.parse(parts[4]);
                task = new Event(description, from, to);
                break;
            default:
                throw new TickException("Invalid task type detected!");
            }

            if (isDone) {
                task.markAsDone();
            }
            taskList.add(task);
        }
        return taskList;
    }
}
