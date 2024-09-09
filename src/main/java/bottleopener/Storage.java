package bottleopener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The {@code BottleOpener.Storage} class handles loading and saving tasks from/to a file.
 * It ensures that task data is persisted between sessions.
 */
public class Storage {
    private Path path;

    /**
     * Constructs a {@code BottleOpener.Storage} object with the specified file path.
     *
     * @param filepath The file path where tasks will be loaded from and saved to.
     */
    public Storage(String filepath) {
        this.path = Paths.get(filepath);
    }

    /**
     * Loads tasks from the specified file into the given {@code BottleOpener.Tasklist}.
     * If the file does not exist, it is created. The method parses the file content and
     * converts each line into a {@code BottleOpener.Task} object, adding it to the list.
     *
     * @param tasks The {@code BottleOpener.Tasklist} to load the data into.
     * @return The updated {@code BottleOpener.Tasklist} after loading the data.
     */
    public Tasklist load(Tasklist tasks) {
        try {
            if (Files.exists(path)) {
                String content = Files.readString(path).trim();
                if (!content.isEmpty()) {
                    String[] lines = content.split("\n");
                    for (String line : lines) {
                        String[] word = line.split("\\|");
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
     * @param tasks The {@code BottleOpener.Tasklist} to be saved.
     */
    public void save(Tasklist tasks) {
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
