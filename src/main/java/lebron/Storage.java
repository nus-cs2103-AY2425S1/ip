package lebron;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Handles the loading and saving of tasks to and from a file.
 * This class is responsible for reading tasks from a file and saving tasks back to it,
 * providing persistent storage for the LeBron ChatBot application.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a Storage object with the specified file path for storing tasks.
     *
     * @param filePath The file path where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * If the file does not exist, an empty task list is returned.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an error occurs while reading from the file.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        final File file = new File(filePath);
        if (!file.exists()) {
            return taskList;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");

            Task task;
            switch (type) {
            case "T":
                task = new ToDos(parts[2].trim());
                break;
            case "D":
                LocalDate date = LocalDate.parse(parts[3].trim());
                task = new Deadlines(parts[2].trim(), date);
                break;
            case "E":
                String[] startEnd = parts[3].split("to");
                LocalDate start = LocalDate.parse(startEnd[0].trim());
                LocalDate end = LocalDate.parse(startEnd[1].trim());
                task = new Event(parts[2].trim(), start, end);
                break;
            default:
                continue;
            }

            if (isDone) {
                task.markAsDone();
            }
            taskList.add(task);
        }
        br.close();
        return taskList;
    }

    /**
     * Saves the current list of tasks to the file specified by the file path.
     * Creates the file and any necessary directories if they do not exist.
     *
     * @param taskList The TaskList containing tasks to be saved to the file.
     */
    public void saveTasks(TaskList taskList) {
        FileWriter fw = null;
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            fw = new FileWriter(filePath);
            ArrayList<Task> tasks = taskList.getTasks();
            for (Task task : tasks) {
                String taskString = task.toFileString();
                fw.write(taskString + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
}
