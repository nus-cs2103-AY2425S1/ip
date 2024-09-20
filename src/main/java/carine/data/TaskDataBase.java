package carine.data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import carine.exceptions.InvalidDateException;
import carine.tasks.Deadline;
import carine.tasks.Event;
import carine.tasks.Task;
import carine.tasks.Todo;

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
        File file = new File("data/tasklist.txt");
        ensureFileExists(file);
        List<String> lines = readFile(file);
        return parseTasks(lines);
    }

    private static void ensureFileExists(File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        assert file.exists() : "File tasklist.txt should exist after attempting to create it.";
    }
    private static List<String> readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lines = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        reader.close();
        return lines;
    }

    private static List<Task> parseTasks(List<String> lines) throws InvalidDateException {
        List<Task> tasks = new ArrayList<>();

        for (String line : lines) {
            tasks.add(parseTask(line));
        }

        return tasks;
    }

    private static Task parseTask(String line) throws InvalidDateException {
        String[] parts = line.split("\\|");
        Task task;

        switch (parts[0]) {
        case "T":
            task = new Todo(parts[2]);
            break;
        case "D":
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            task = new Event(parts[2], parts[3], parts[4]);
            break;
        default:
            throw new IllegalStateException("Unexpected task type: " + parts[0]);
        }

        if (parts[1].equals("1")) {
            task.markAsDone();
        }

        return task;
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

