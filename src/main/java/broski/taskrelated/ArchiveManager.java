package broski.taskrelated;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import broski.task.Deadline;
import broski.task.Event;
import broski.task.Task;
import broski.task.Todo;

/**
 * Class that holds all functionality for the saving and loading of tasks into hard disk.
 */
public class ArchiveManager {

    private static final String FILE_PATH = "./data/archive.txt";

    /**
     * Saves task to hard disk as txt file.
     * @param tasks list of tasks to be saved to hard disk.
     */
    public void saveTasks(List<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // Create directories if they don't exist

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks) {
                writer.write(task.toStringSave()); // Format task as needed
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks back into the tasklist from txt file.
     * @return ArrayList of tasks to be copied back into tasklist.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return tasks;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                tasks.add(task);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses each line in the txt file to return it to the intended format.
     * @param line each line of the txt file.
     * @return the specific task indicated by that line in the txt file.
     */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3 || !(parts[0].equals("T") || parts[0].equals("D")
                    || parts[0].equals("E"))) {
                throw new IOException("Invalid task format.");
            }
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            if (type.equals("T")) {
                Task todo = new Todo(description);
                if (isDone) {
                    todo.mark();
                }
                return todo;
            } else if (type.equals("D")) {
                Task deadline = new Deadline(description, LocalDateTime.parse(parts[3]));
                if (isDone) {
                    deadline.mark();
                }
                return deadline;
            } else {
                Task event = new Event(description, LocalDateTime.parse(parts[3]) ,
                        LocalDateTime.parse(parts[4]));
                if (isDone) {
                    event.mark();
                }
                return event;
            }
        } catch (IOException e) {
            System.out.println("Skipping corrupted line: " + line);
            return null;
        }
    }
}

