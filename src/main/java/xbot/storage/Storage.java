package xbot.storage;

import xbot.task.Task;
import xbot.TaskList;
import xbot.parser.Parser;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The Storage class handles loading and saving tasks to and from a file in the XBot application.
 * It manages file operations such as reading existing tasks, saving new tasks, and creating the necessary files and directories.
 */
public class Storage {
    private static final Path DATA_PATH = Paths.get("data", "XBot.txt");

    /**
     * Loads tasks from the data file into the TaskList.
     * If the file does not exist, it will be created along with the necessary directories.
     *
     * @throws IOException If an I/O error occurs during the loading process.
     */
    public void loadTask() throws IOException {
        TaskList list = new TaskList();
        if (Files.exists(DATA_PATH)) {
            //Add all task in data/XBot.txt to the list
            try (Scanner scanner = new Scanner(DATA_PATH.toFile())) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = Parser.parseTask(line);
                    if (task != null) {
                        list.add(task);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
                throw new IOException("File not found", e);
            }
        } else {
            createFile();
        }
    }

    /**
     * Save tasks from the TaskList into the data file.
     * Each task is saved as a string representation suitable for file storage.
     *
     * @param taskList The TaskList containing tasks to be saved.
     */
    public void saveTask(TaskList taskList) {
        try (FileWriter writer = new FileWriter(DATA_PATH.toFile())) {
            for (Task task : taskList.getAllTask()) {
                writer.write(Parser.taskToFileString(task) + System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Creates the necessary file and directory for storing tasks if they do not already exist.
     * The directory is created at './data', and the file is named 'XBot.txt'.
     */
    public void createFile() {
        Path directoryPath = Paths.get("./data");
        Path filePath = directoryPath.resolve("XBot.txt");

        try {
            // Check if the directory exists, and create it if it doesn't
            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
            }

            // Check if the file exists, and create it if it doesn't
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
