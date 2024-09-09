package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The duke.Storage class handles the loading and saving of tasks from and to a file.
 * It is responsible for managing the persistence of the task list.
 */
public class Storage {


    private final ArrayList<IndividualTask> tasks = new ArrayList<IndividualTask>();

    private final String path;

    /**
     * Constructs a duke.Storage object with the specified file path.
     * It automatically attempts to load tasks from the specified file.
     *
     * @param path The file path where tasks are stored.
     */
    public Storage(String path) {
        this.path = path;
        this.loadTasksFromFile();
    }

    /**
     * Loads tasks from the specified file into the task list.
     * If the directory or file does not exist, it provides instructions for creating them.
     * The tasks are read from the file and converted into duke.IndividualTask objects,
     * which are added to the task list.
     */
    public void loadTasksFromFile() {
        File directory = new File("./data");
        File file = new File(path);
        if (!directory.exists()) {
            System.out.println("No existing directory found. Creating a 'data' directory.");
            directory.mkdirs(); // Create the directory
        }

        // Check if the file exists, if not, create it
        if (!file.exists()) {
            System.out.println("No existing data file found. Creating the file 'duke.txt' in the data directory.");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String tasks = scanner.nextLine();
                String[] parts = tasks.split(" \\| ");
                String typeOfTask = parts[0];
                boolean isTaskDone = parts[1].equals("1");
                String descriptionOfTask = parts[2];
                IndividualTask task = switch (typeOfTask) {
                case "T" -> new ToDo(descriptionOfTask);
                case "D" -> new Deadline(descriptionOfTask, parts[3]);
                case "E" -> new Event(descriptionOfTask, parts[3], parts[4]);
                default -> null;
                };
                if (task == null) {
                    continue;
                }
                if (isTaskDone) {
                    task.setMarkStatus("mark");
                }
                this.tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("The data file not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("There is no data in the file");
        }
    }

    /**
     * Returns the list of tasks that have been loaded from the file.
     *
     * @return The list of tasks.
     */
    public ArrayList<IndividualTask> load() {
        return this.tasks;
    }

    /**
     * Saves the current list of tasks to the specified file.
     * The tasks are written to the file in a format that can be loaded back into the application.
     *
     * @param curList The current list of tasks to be saved.
     */
    public void saveTasksToFile(ArrayList<IndividualTask> curList) {
        try {
            FileWriter writer = new FileWriter(path);
            for (IndividualTask task : curList) {
                writer.write(task.saveToFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error in saving task: " + e.getMessage());
        }
    }
}
