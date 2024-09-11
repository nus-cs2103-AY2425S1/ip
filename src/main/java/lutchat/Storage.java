package lutchat;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class is responsible for saving and loading tasks from a file.
 * It handles the persistence of tasks between sessions of the application.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file and returns them as an ArrayList.
     * If the file does not exist, an empty task list is returned.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();

        File file = new File(filePath);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
            return taskList;
        }

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            String[] parts = data.split(" \\| ");
            if (parts.length < 3) {
                System.out.println("Skipping corrupted data: " + data);
                continue;
            }
            assert parts.length >= 3 : "Unexpected task format in file.";

            String taskType = parts[0];
            boolean done = parts[1].equals("1");
            String desc = parts[2];

            Task task = null;
            switch (taskType) {
            case "T":
                task = new Todo(desc);
                break;
            case "D":
                if (parts.length < 4) {
                    System.out.println("Skipping corrupted line: " + data);
                    continue;
                }
                task = new Deadline(desc, parts[3]);
                break;
            case "E":
                if (parts.length < 5) {
                    System.out.println("Skipping corrupted line: " + data);
                    continue;
                }
                task = new Event(desc, parts[3], parts[4]);
                break;
            default:
                System.out.println("Skipping unknown task type: " + taskType);
                continue;
            }

            if (task != null) {
                if (done) {
                    task.markAsDone();
                }
                taskList.add(task);
            }
        }
        sc.close();
        return taskList;
    }

    /**
     * Saves the current list of tasks to the specified file.
     *
     * @param taskList The TaskList object containing the tasks to be saved.
     */
    public void save(TaskList taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList.getTasks()) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Oh no! An error occurred while saving tasks...");
        }
    }
}
