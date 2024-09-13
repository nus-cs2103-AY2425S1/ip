package bottleopener.myapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import bottleopener.task.Deadline;
import bottleopener.task.Event;
import bottleopener.task.Task;
import bottleopener.task.Tasklist;
import bottleopener.task.ToDo;

/**
 * The {@code Storage} class handles loading and saving tasks from/to a file.
 * It ensures that task data is persisted between sessions.
 */
public class Storage {
    private final Path path;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filepath The file path where tasks will be loaded from and saved to.
     */
    public Storage(String filepath) {
        this.path = Paths.get(filepath);
    }

    /**
     * Loads tasks from a file and populates the given {@code Tasklist}.
     * The file is read from the specified path, and each line is interpreted
     * as a task.
     *
     * <p>If the file does not exist, the method will attempt to create the necessary
     * directories and the file.</p>
     *
     * @param tasks The {@code Tasklist} object where the loaded tasks will be added.
     * @return The updated {@code Tasklist} containing tasks loaded from the file.
     */
    public Tasklist load(Tasklist tasks) {
        try {
            if (Files.exists(path)) {
                String content = Files.readString(path).trim();
                if (!content.isEmpty()) {
                    String[] lines = content.split("\n");
                    for (String line : lines) {
                        String[] word = line.split("\\|");
                        assert word.length >= 3 : "Invalid format";
                        String type = word[0];
                        boolean status = word[1].contains("X");

                        if (type.equals("T")) {
                            Task newTask = new ToDo(word[2], status);
                            tasks.addTask(newTask);
                        } else if (type.equals("D")) {
                            Task newTask = new Deadline(word[2], status, word[3]);
                            tasks.addTask(newTask);
                        } else if (type.equals("E")) {
                            Task newTask = new Event(word[2], status, word[3], word[4]);
                            tasks.addTask(newTask);
                        } else {
                            assert false : "Unknown task type";
                        }
                    }
                }
            } else {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.out.println("Invalid");
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the specified file.
     * The tasks are serialized into a specific format and written to the file.
     *
     * @param tasks The {@code Tasklist} to be saved.
     */
    public void save(Tasklist tasks) {
        assert tasks != null : "Tasklist cannot be null";

        String outputFile = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task curr = tasks.getTask(i);
            outputFile = outputFile + String.format("%s|%s|%s|%s%n", curr.getType(), curr.getStatusIcon(),
                    curr.getDescription(), curr.getTime());
        }
        try {
            Files.writeString(this.path, outputFile);
        } catch (IOException e) {
            System.out.println("Invalid");
        }
    }

}
