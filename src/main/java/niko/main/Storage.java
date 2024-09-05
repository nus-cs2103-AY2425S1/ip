package niko.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import niko.task.Deadline;
import niko.task.Event;
import niko.task.Task;
import niko.task.Todo;




/**
 * Handles the loading and saving of tasks to a file.
 */
public class Storage {

    /** The file path where tasks are stored. */
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the list of tasks to the file.
     *
     * @param tasks The tasks to be written to the file.
     */
    public void write(String tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("example.txt"))) {
            writer.write(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the tasks from the file and returns them as an ArrayList of Task objects.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws IOException If an I/O error occurs during loading.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            String[] parts = line.split(", ");
            for (String part : parts) {
                Task task = parseTask(part);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }

        reader.close();
        return tasks;
    }

    /**
     * Parses a line from the file and returns the corresponding Task object.
     *
     * @param line The line to be parsed.
     * @return The Task object corresponding to the line, or null if the line cannot be parsed.
     */
    private Task parseTask(String line) {
        char typeChar = line.charAt(1);
        boolean isDone = line.charAt(4) == 'X';

        String description;
        Task task = null;

        switch (typeChar) {
        case 'T':
            description = line.substring(7).trim();
            task = new Todo(description);
            break;
        case 'D':
            description = line.substring(7, line.indexOf("(by:")).trim();
            String byDateString = line.substring(line.indexOf("(by:") + 5, line.length() - 1).trim();
            task = new Deadline(description, byDateString);
            break;
        case 'E':
            description = line.substring(7, line.indexOf("(from:")).trim();
            String fromDateString = line.substring(line.indexOf("(from:") + 7, line.indexOf("to:")).trim();
            String toDateString = line.substring(line.indexOf("to:") + 4, line.length() - 1).trim();
            task = new Event(description, fromDateString, toDateString);
            break;
        default:
            return null;
        }
        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}
