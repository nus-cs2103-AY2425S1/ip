package agave.Util;

import agave.Task.Task;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * The Storage class manages the saving and loading of tasks to and from a file.
 */
public class Storage {

    private File file;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The file path where tasks will be stored and loaded from.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error when creating file: " + e.getMessage());
        }
    }
    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an error occurs when writing to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        assert tasks != null : "Tasks cannot be null";
        BufferedWriter bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Task task : tasks) {
                bufferedWriter.write(task.toWriteFormat());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error when writing to file: " + e.getMessage());
        } finally {
            if(bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }

    /**
     * Loads the list of tasks from the file.
     *
     * @return The list of tasks loaded from the file.
     * @throws IOException If an error occurs when reading from the file.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                Task task = Task.parseTask(line);
                tasks.add(task);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error when reading from file: " + e.getMessage());
        }
        return tasks;
    }
}
