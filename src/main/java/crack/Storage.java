package crack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles reading and writing tasks to and from a file.
 * It manages the persistence of tasks, ensuring that tasks can be saved and
 * loaded
 * between sessions.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath the file path where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to a file. If the file or directory does not exist,
     * it creates them. Each task is saved in a specific format that can be loaded
     * later.
     *
     * @param tasks the list of tasks to be saved.
     * @param ui    the user interface to show error messages if the save operation
     *              fails.
     */
    public void saveTasks(ArrayList < Task > tasks, Ui ui) {
        try {
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdirs(); // Create the directory if it doesn't exist
            }

            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it doesn't exist
            }

            FileWriter writer = new FileWriter(filePath);
            for (Task task: tasks) {
                writer.write(task.toSaveString() + System.lineSeparator()); // Write each task in save format
            }
            writer.close();
        } catch (IOException e) {
            ui.showError("Unable to save tasks.");
        }
    }

    /**
     * Loads the list of tasks from the file. If the file does not exist, it returns
     * an empty task list.
     * The method parses each line in the file and recreates the corresponding
     * tasks.
     *
     * @param ui the user interface to show error messages in case of corrupted data
     *           or file issues.
     * @return an ArrayList of tasks loaded from the file.
     * @throws IOException if the file format is corrupted or there are issues
     *                     reading the file.
     */
    public ArrayList < Task > loadTasks(Ui ui) throws IOException {
        ArrayList < Task > tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // No file exists, so return an empty task list
        }

        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(" \\| "); // Split the task into parts based on the save format
            switch (parts[0]) {
            case "T": // Todo task
                Todo todo = new Todo(parts[2]);
                if (parts[1].equals("1")) {
                    todo.markAsDone(); // Mark the task as done if indicated in the file
                }
                tasks.add(todo);
                break;
            case "D": // Deadline task
                Deadline deadline = new Deadline(parts[2], parts[3]);
                if (parts[1].equals("1")) {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
                break;
            case "E": // Event task
                Event event = new Event(parts[2], parts[3], parts[4]);
                if (parts[1].equals("1")) {
                    event.markAsDone();
                }
                tasks.add(event);
                break;
            default:
                throw new IOException("Corrupted task format"); // If the task type is unrecognized, throw an
                // exception
            }
        }
        fileScanner.close();
        return tasks;
    }
}
