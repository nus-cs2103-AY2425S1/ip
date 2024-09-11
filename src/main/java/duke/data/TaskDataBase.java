package duke.data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import duke.exceptions.InvalidDateException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * The `TaskDataBase` class provides methods to load and save tasks to and from a file.
 */
public class TaskDataBase {

    /**
     * Loads tasks from a file and returns them as a list.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     * @throws InvalidDateException If a date in the file is invalid or cannot be parsed.
     */
    public static List<Task> load() throws IOException, InvalidDateException {
        List<Task> tasks = new ArrayList<>();
        File file = new File("data/tasklist.txt");

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        assert file.exists() : "File tasklist.txt should exist after attempting to create it.";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            Task task;

            switch (parts[0]) {
            case "T":
                assert parts.length >= 3 : "Data format in tasklist.txt is incorrect, todo should contain 3 parts";
                task = new Todo(parts[2]);
                break;
            case "D":
                assert parts.length >= 3 : "Data format in tasklist.txt is incorrect, deadline should contain 4 parts";
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                assert parts.length >= 3 : "Data format in tasklist.txt is incorrect, event should contain 5 parts";
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                throw new IllegalStateException("Unexpected task type: " + parts[0]);
            }

            if (parts[1].equals("1")) {
                task.markAsDone();
            }

            tasks.add(task);
        }

        reader.close();
        return tasks;
    }

    /**
     * Saves a list of tasks to a file.
     *
     * @param tasks The list of tasks to be saved to the file.
     */
    public static void save(List<Task> tasks) throws IOException {
        File file = new File("data/tasklist.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (Task task : tasks) {
            writer.write(task.toDataFormat());
            writer.newLine();
        }

        writer.close();
    }

}

