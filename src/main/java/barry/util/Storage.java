package barry.util;

import barry.task.Deadline;
import barry.task.Event;
import barry.task.Task;
import barry.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the database that store's the user's tasks to and from a file.
 *
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into an ArrayList of all tasks.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an input or output error occurs.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> allTasks = new ArrayList<>();
        File file = new File(filePath);

        // Create file if it does not exist
        if (!file.exists()) {
            createFile(file);
            return allTasks;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            /*
            [0]: clover.task.Task, clover.task.Deadline or clover.task.Event
            [1]: 1 or 0
            [2]: task
            [3]: due date
            [4]: time
             */
            String[] split = line.split(" \\| "); //split up line at even "|"
            Task task;

            switch (split[0]) {
                case "T": // todo
                    task = new Todo(split[2]);
                    break;

                case "D": // deadline
                    task = new Deadline(split[2], split[3]);
                    break;

                case "E": // event
                    task = new Event(split[2], split[3], split[4]);
                    break;

                default: // none of the above cases
                    throw new IOException("data file is corrupt");
            }

            if (split[1].equals("0")) {
                task.markAsUndone();
            } else if (split[1].equals("1")) {
                task.markAsDone();
            }

            allTasks.add(task);
        }
        scanner.close();
        return allTasks;
    }

    /**
     * Creates a new file.
     *
     * @param file The file to be created.
     * @throws IOException If an input or output error occurs.
     */
    public void createFile(File file) throws IOException {
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
    }

    /**
     * Saves the current list of all tasks to the file.
     *
     * @param tasks An ArrayList of tasks to be saved to the file.
     * @throws IOException If an input or output error occurs.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.saveFormat() + System.lineSeparator());
        }
        writer.close();
    }
}