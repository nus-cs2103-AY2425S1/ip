package kafka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a storage for managing tasks. It allows loading tasks from a file and saving tasks back to the file.
 */
public class Storage {

    /**
     * The path to the file where tasks are stored.
     */
    public final String filePath;

    /**
     * Constructor that takes the file path for storing tasks.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file path.
     *
     * @throws FileNotFoundException If the file is not found at the specified path.
     * @return An ArrayList containing loaded tasks.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        this.getNewFile(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        this.printFileContents(tasks);
        return tasks;
    }

    /**
     * Reads the contents of the file and populates the provided ArrayList with loaded tasks.
     *
     * @param tasks The ArrayList to be populated with loaded tasks.
     * @throws FileNotFoundException If the file is not found at the specified path.
     */
    private void printFileContents(ArrayList<Task> tasks) throws FileNotFoundException {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] fileContent = s.nextLine().trim().split(" \\| ");
                String taskType = fileContent[0];
                boolean isDone = Boolean.parseBoolean(fileContent[1]);
                String description = fileContent[2];
                switch (taskType) {
                    case "T":
                        Task todo = new Todo(description, isDone);
                        tasks.add(todo);
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(fileContent[3]);
                        Task deadline = new Deadline(description, by, isDone);
                        tasks.add(deadline);
                        break;
                    case "E":
                        LocalDateTime from = LocalDateTime.parse(fileContent[3]);
                        LocalDateTime to = LocalDateTime.parse(fileContent[4]);
                        Task event = new Event(description, from, to, isDone);
                        tasks.add(event);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Writes the provided tasks to the specified file path.
     *
     * @param tasks The ArrayList containing tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void writeToFile(ArrayList<Task> tasks) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task t : tasks) {
                if (t instanceof Todo todo) {
                    fw.write("T | " + todo.isDone + " | "
                            + todo.description + System.lineSeparator());
                } else if (t instanceof Deadline deadline) {
                    fw.write("D | " + deadline.isDone + " | "
                            + deadline.description + " | " + deadline.by + System.lineSeparator());
                } else if (t instanceof Event event) {
                    fw.write("E | " + event.isDone + " | "
                            + event.description + " | " + event.from + " | " + event.to
                            + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Creates a new file at the specified path if it doesn't exist, including creating any necessary parent directories.
     *
     * @param filePath The path to the file to be created.
     */
    private void getNewFile(String filePath) {
        File f = new File(filePath);
        try {
            f.getParentFile().mkdirs();
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }
}

