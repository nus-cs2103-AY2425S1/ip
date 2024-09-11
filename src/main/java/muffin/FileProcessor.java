package muffin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handled reading tasks from a file and writing tasks to a file.
 * It processes Todos, Deadlines, and Events by reading their data from a file and
 * saving the updated task list back to the file.
 */
public class FileProcessor {

    /**
     * Reads tasks from a file located at the specified file path. The file is
     * expected to have lines representing different types of tasks, each line
     * containing task details separated by a pipe '|' character.
     *
     * @param filePath The path of the file to read tasks from.
     * @return An ArrayList of tasks read from the file.
     */
    public ArrayList<Task> readFromFile(String filePath) {
        assert filePath != null;

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);

            if (!file.exists()) {
                return tasks;
            }

            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line: lines) {
                String[] parts = line.split("\\|");
                try {
                    Task task = TaskFactory.createTask(parts);
                    tasks.add(task);
                } catch (MuffinException e) {
                    System.err.println("Invalid task in file: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    /**
     * Writes the list of tasks to a file at the specified file path. The tasks are serialized
     * into lines of text, each line representing a task and its details.
     *
     * @param filePath The path of the file to write the tasks to.
     * @param tasks An ArrayList of tasks to write to the file.
     */
    public void writeToFile(String filePath, ArrayList<Task> tasks) {
        assert filePath != null;
        assert tasks != null;

        try {
            ArrayList<String> content = new ArrayList<>();

            for (Task task : tasks) {
                content.add(task.toFormattedString());
            }

            Files.write(Paths.get(filePath), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
