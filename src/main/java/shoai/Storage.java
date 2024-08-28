package shoai;
import shoai.Task; // Imports the Task class for task handling
import shoai.Parser; // Imports the Parser class for converting tasks to and from file strings
import shoai.ShoAIException; // Imports the ShoAIException class for handling exceptions
import java.io.*; // Imports classes for file handling (BufferedReader, BufferedWriter, File, FileReader, FileWriter, IOException)
import java.util.ArrayList; // Imports ArrayList to store and manipulate the task list


/**
 * Handles the storage and retrieval of tasks to and from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws ShoAIException If there is an error reading from the file.
     */
    public ArrayList<Task> loadTasks() throws ShoAIException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // No file to load, return empty list
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Parser.fileStringToTask(line));
            }
        } catch (IOException e) {
            throw new ShoAIException("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the specified tasks to the file.
     *
     * @param tasks The ArrayList of tasks to save.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Create the directory if it doesn't exist

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (Task task : tasks) {
                writer.write(Parser.taskToFileString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
