package Dook.Storage;

import Dook.Tasks.TaskList;
import Dook.Tasks.Task;
import Dook.Tasks.Event;
import Dook.Tasks.Deadline;
import Dook.Tasks.Todo;
import Dook.DookException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class deals with the loading of tasks from the file and saving tasks in the file.
 */
public class Storage {

    private File savedTasks;
    public Storage(String filePath) {
        this.savedTasks = new File(filePath);
    }

    /**
     * Ensures that the directory and file for saving tasks exist. If the directory or file does not exist,
     * the method will create them.
     *
     * @throws IOException If an I/O error occurs during the creation of the file or directories.
     */
    public void setup() throws IOException {
        File directory = savedTasks.getParentFile();

        // Ensure directory exists, create it if it doesn't
        if (directory != null && !directory.exists()) {
            directory.mkdirs();  // Creates the directory if it doesn't exist
        }

        // Ensure file exists, create it if it doesn't
        if (!savedTasks.exists()) {
            savedTasks.createNewFile();  // Creates the file if it doesn't exist
        }
    }

    /**
     * Loads the tasks from the saved file and returns them as an ArrayList.
     * The tasks are created based on the String format used in the file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws FileNotFoundException If the file does not exist.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner reader = new Scanner(this.savedTasks);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] components = line.split(" \\| ");
            String taskType = components[0];
            boolean isDone = components[1].equals("1");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Task task;
            switch (taskType) {
                case "T":
                    task = new Todo(components[2]);
                    break;
                case "D" :
                    task = new Deadline(components[2], LocalDateTime.parse(components[3], formatter));
                    break;
                case "E" :
                    task = new Event(components[2], LocalDateTime.parse(components[3], formatter), LocalDateTime.parse(components[4], formatter));
                    break;
                default:
                    continue;
            }
            if (isDone) {
                task.markAsDone();
            }

            tasks.add(task);
        }
        reader.close();
        return tasks;
    }

    /**
     * Writes the list of tasks to the saved file.
     *
     * @param tasks The list of tasks to be written to the file.
     * @throws DookException If a problem occurs while retrieving task data.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void write(TaskList tasks) throws DookException,IOException {
        FileWriter writer = new FileWriter(savedTasks);
        for (int i = 0; i < tasks.numOfTasks(); i++) {
            writer.write(tasks.getTask(i).fileFormatted() + System.lineSeparator());
        }
        writer.close();
    }
}
