package donna;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import donna.task.Deadline;
import donna.task.Event;
import donna.task.Task;
import donna.task.TaskList;
import donna.task.ToDo;
/**
 * Handles the saving and loading of tasks to and from a file.
 * Manages the file and directory paths for storing task data.
 */
public class Storage {
    private final String directoryPath;
    private final String filePath;

    /**
     * Constructs a Storage instance with the specified directory and file paths.
     *
     * @param directoryPath The path of the directory where the file is stored.
     * @param filePath The path of the file where tasks are saved.
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to a file.
     * Creates the directory if it does not exist.
     *
     * @param tasks The TaskList containing tasks to be saved.
     */
    public void saveTasks(TaskList tasks) {
        try {
            Files.createDirectories(Paths.get(this.directoryPath)); //ensure directory exists
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.filePath))) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    writer.write(task.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("An error has occurred in saving the tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file.
     *
     * @return An ArrayList of tasks loaded from the file.
     *         Empty ArrayList is returned if file does not exist or is corrupted.
     * @throws DonnaException If there is an error while creating tasks from the file data.
     */
    public ArrayList<Task> loadTasks() throws DonnaException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Path filePath = Paths.get(this.filePath);
            if (!Files.exists(filePath)) { //ensure that the directory exists- if not, it will be created
                return tasks; //empty arrayList is returned when no file exists
            }

            File file = new File(String.valueOf(filePath));
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] inWords = line.split(" \\| ");
                String taskType = inWords[0];
                boolean isDone = inWords[1].equals("1");
                Task task = null;

                switch (taskType) {
                case "T":
                    task = new ToDo(inWords[2]);
                    break;
                case "D":
                    task = new Deadline(inWords[2], inWords[3]);
                    break;
                case "E":
                    task = new Event(inWords[2], inWords[3], inWords[4]);
                    break;
                default:
                    throw DonnaException.invalidTaskType(taskType);
                }

                if (task != null) {
                    if (isDone) {
                        task.markDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | DonnaException e) {
            return new ArrayList<Task>(); //return empty arrayList to start afresh
        }
        return tasks;
    }
}
