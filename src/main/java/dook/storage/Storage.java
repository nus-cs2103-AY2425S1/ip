package dook.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import dook.DookException;
import dook.tasks.Deadline;
import dook.tasks.Event;
import dook.tasks.Task;
import dook.tasks.TaskList;
import dook.tasks.Todo;

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
            directory.mkdirs(); // Creates the directory if it doesn't exist
        }

        // Ensure file exists, create it if it doesn't
        if (!savedTasks.exists()) {
            savedTasks.createNewFile(); // Creates the file if it doesn't exist
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

            generateTasks(line, tasks);
        }
        reader.close();
        return tasks;
    }

    /**
     * Generates and adds a task to the list based on the string representation of the task.
     * The task is created based on the components of the input line.
     * If the task type is not recognized, it skips the addition of the task.
     *
     * @param line  The string representing the task in the file format.
     * @param tasks The list of tasks to which the generated task will be added.
     */
    private void generateTasks(String line, ArrayList<Task> tasks) {
        String[] components = line.split(" \\| ");
        String taskType = components[0];
        boolean isDone = components[1].equals("1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Task task = getType(taskType, components, formatter, isDone);

        if (task == null) {
            return;
        }

        tasks.add(task);
    }

    /**
     * Creates and returns a task based on the task type and components provided.
     * Returns null if the task type is not recognized.
     *
     * @param taskType  The type of the task (e.g., "T" for Todo, "D" for Deadline, "E" for Event).
     * @param components The components of the task, split from the line in the file.
     * @param formatter The DateTimeFormatter used to parse date and time strings.
     * @param isDone    Indicates whether the task is marked as done.
     * @return The task created based on the task type and components, or null if the task type is not recognized.
     */
    private Task getType(String taskType, String[] components, DateTimeFormatter formatter, boolean isDone) {
        Task task = null;
        try {
            switch (taskType) {
            case "T":
                task = new Todo(components[2]);
                break;
            case "D":
                task = new Deadline(components[2], LocalDateTime.parse(components[3], formatter));
                break;
            case "E":
                task = new Event(components[2], LocalDateTime.parse(components[3], formatter),
                        LocalDateTime.parse(components[4], formatter));
                break;
            default:
                return null;
            }

            if (isDone) {
                task.markAsDone();
            }


        } catch (DookException e) {
            System.out.println(e.getMessage());
        }

        return task;
    }

    /**
     * Writes the list of tasks to the saved file.
     *
     * @param tasks The list of tasks to be written to the file.
     * @throws DookException If a problem occurs while retrieving task data.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void write(TaskList tasks) throws DookException, IOException {
        FileWriter writer = new FileWriter(savedTasks);
        for (int i = 0; i < tasks.numOfTasks(); i++) {
            writer.write(tasks.getTask(i).fileFormatted() + System.lineSeparator());
        }
        writer.close();
    }
}
