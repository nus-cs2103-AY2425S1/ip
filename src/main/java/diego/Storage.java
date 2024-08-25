package diego;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to and from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage object.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates the file if it does not exist.
     */
    public void createFileIfNotExists() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws FileNotFoundException If the file cannot be found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                continue;
            }

            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            try {
                switch (taskType) {
                    case "T":
                        tasks.add(new Todo(description));
                        break;
                    case "D":
                        tasks.add(new Deadline(description, parts[3]));
                        break;
                    case "E":
                        tasks.add(new Event(description, parts[3], parts[4]));
                        break;
                }
                if (isDone) {
                    tasks.get(tasks.size() - 1).mark();
                }
            } catch (IndexOutOfBoundsException e) {
                // Ignore errors or handle appropriately
            }
        }
        scanner.close();
        return tasks;
    }

    /**
     * Saves the task list to the file.
     *
     * @param tasks The task list to be saved.
     */
    public void save(TaskList tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks.getAll()) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
