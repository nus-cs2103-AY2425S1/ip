import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class Storage {
    private static final String FILE_PATH = "./data/echo-bot.txt";
    private static final String DIR_PATH = "./data/";

    // Method to ensure the directory and file exist
    public static void ensureFileExists() {
        File dir = new File(DIR_PATH);
        if (!dir.exists()) {
            dir.mkdirs(); // Create the directory if it doesn't exist
        }

        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it doesn't exist
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    // Method to save tasks to a file
    public static void saveTasksToFile(TaskList tasks) {
        ensureFileExists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks.getTasks()) {
                writer.println(task.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Load tasks from file when starting the program
    public static void loadTasksFromFile(TaskList tasks) {
        ensureFileExists();
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) {
            // No file to load from
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(" \\| ");
                    Task task = null;
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
                            throw new IllegalArgumentException("Unknown task type");
                    }
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.addTask(task);
                } catch (Exception e) {
                    System.out.println("Corrupted line in file: " + line);
                    // Optionally, delete or reset the file if corruption detected:
                    // new File(FILE_PATH).delete(); // Resets the file
                    // System.out.println("File reset due to corruption.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}
