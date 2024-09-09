package devon;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to and from a file-based database.
 * Manages the task database file operations, including creation and data formatting.
 */
public class Storage {
    protected static final String DIRECTORY_PATH = "./data";
    protected static final String DB_PATH = String.valueOf(Paths.get(Storage.DIRECTORY_PATH, "devon_tasks.txt"));
    protected static final String DB_DELIMITER = "\\|";

    protected static final DateTimeFormatter DATE_TIME_FORMATTER_FOR_EXTERNAL_INPUT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static final DateTimeFormatter DATE_TIME_FORMATTER_FOR_DB =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    /**
     * Constructs a Storage object.
     */
    public Storage() { }

    /**
     * Loads tasks from the database file.
     * If the file does not exist, it creates a new task database file.
     *
     * @return An ArrayList of strings representing the tasks.
     */
    protected ArrayList<String> loadTasksFromDatabase() {
        ArrayList<String> tasks = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(new File(Storage.DB_PATH));
            while (fileReader.hasNextLine()) {
                tasks.add(fileReader.nextLine());
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            this.createTaskDatabase();
        }
        return tasks;
    }

    /**
     * Creates a new task database file and directory if they do not exist.
     */
    private void createTaskDatabase() {
        new File(Storage.DIRECTORY_PATH).mkdir();
        try {
            new File(Storage.DB_PATH).createNewFile();
        } catch (IOException e) {
            System.out.println("Error: Cannot create database!");
        }
    }

    /**
     * Saves the tasks from a TaskList to the database file.
     *
     * @param tasks The TaskList object containing tasks to be saved.
     * @throws IOException If there is an error writing to the database file.
     */
    protected void saveTasksToDatabase(TaskList tasks) throws IOException {
        FileWriter filewriter = new FileWriter(Storage.DB_PATH);
        BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            bufferedWriter.write(tasks.getTask(i).dbReadableFormat());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        filewriter.close();
    }
}
