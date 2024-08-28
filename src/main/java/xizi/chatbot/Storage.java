package xizi.chatbot;// Do not use wildcard imports

import xizi.chatbot.task.Deadline;
import xizi.chatbot.task.Event;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.Todo;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the loading and saving of tasks from/to a file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the file specified by {@code filePath}.
     *
     * @return A list of tasks loaded from the file. Returns an empty list if the file does not exist.
     * @throws IOException If an I/O error occurs.
     * @throws XiziException If the data in the file is corrupted or in an unexpected format.
     */
    public List<Task> load() throws IOException, XiziException {
        List<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            return tasks; // Return an empty task list if file doesn't exist
        }
        BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
        try {
            String line = reader.readLine();
            while (line != null) {
                Task task = getTask(line);
                line = reader.readLine();
                tasks.add(task);
            }
        } finally {
            reader.close(); // Ensure the reader is closed to prevent resource leaks
        }

        return tasks;
    }

    /**
     * Parses a line from the file to create a Task object.
     *
     * @param line The line from the file representing a task.
     * @return The task created from the line.
     * @throws XiziException If the task type is unknown or the data is corrupted.
     */
    private static Task getTask(String line) throws XiziException {
        String[] parts = line.split(" \\| ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"); // same as output format in xizi class
        Task task;
        switch (parts[0]) {
        case "T":
            task = new Todo(parts[2]);
            break;
        case "D":
            LocalDateTime ddl = LocalDateTime.parse(parts[3], formatter);
            task = new Deadline(parts[2], ddl);
            break;
        case "E":
            LocalDateTime from = LocalDateTime.parse(parts[3], formatter);
            LocalDateTime to = LocalDateTime.parse(parts[4], formatter);
            task = new Event(parts[2], from, to);
            break;
        default:
            throw new XiziException("File data corrupted: Unknown task type.");
        }
        if (parts[1].equals("1")) {
            task.markDone();
        }
        return task;
    }

    /**
     * Appends a task to the file specified by {@code filePath}.
     *
     * @param task The task to be appended to the file.
     * @throws IOException If an I/O error occurs.
     */
    public void appendTask(Task task) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
            writer.write(task.toFileFormat());
            writer.newLine();
        }
    }

    /**
     * Saves the list of tasks to the file specified by {@code filePath}.
     * This method overwrites any existing data in the file.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws IOException If an I/O error occurs.
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
    }
}
