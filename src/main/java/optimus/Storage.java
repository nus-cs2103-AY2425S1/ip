package optimus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Let ChatGPT check and suggest comments and JavaDocs according to CS2103T style guide
/**
 * Handles loading and saving tasks to and from a file.
 */
public class Storage {
    private String filePath; // File path for storing the task data
    private Ui ui;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Loads tasks from the file if it exists.
     * If the file or directory does not exist, create them.
     * This method was partially inspired by code examples provided by ChatGPT and W3Schools.
     * Solution below inspired by https://www.w3schools.com/java/java_files_create.asp
     * ChatGPT suggested to catch IOException
     *
     * @return The list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while loading the file.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            // Create directory and file if they do not exist
            File directory = new File("data");
            File file = new File(filePath);

            if (!directory.exists()) {
                directory.mkdir();
            }

            if (!file.exists()) {
                //remove after testing
                file.createNewFile();
                ui.showToUser("Created new data file optimus.txt");
            } else {
                // Read tasks from the file
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split("\\|");
                    String taskType = parts[0].trim();

                    Task task;
                    if (taskType.equals("T")) {
                        String description = parts[2].trim();
                        task = new Todo(description);
                    } else if (taskType.equals("D")) {
                        String description = parts[2].trim();
                        String by = parts[3].trim();
                        try {
                            task = new Deadline(description, by);
                        } catch (OptimusException e) {
                            ui.showToUser("Error loading deadline task: " + e.getMessage());
                            continue;
                        }
                    } else {
                        String description = parts[2].trim();
                        String from = parts[3].trim();
                        String to = parts[4].trim();
                        try {
                            task = new Event(description, from, to);
                        } catch (OptimusException e) {
                            ui.showToUser("Error loading event task: " + e.getMessage());
                            continue;
                        }
                    }
                    // Mark task as done if indicated in the file
                    task.isDone = parts[1].trim().equals("1");
                    taskList.add(task);
                }
                scanner.close();
                ui.showToUser("Loaded tasks from file optimus.txt");
            }
        } catch (IOException e) {
            // Handle file I/O exceptions
            ui.showToUser("Error loading tasks from file: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            // Handle invalid file formatting
            // Suggested by ChatGPT
            ui.showToUser("Invalid task format in file.");
        }
        return taskList;
    }

    /**
     * Saves the tasks in the taskList to the file.
     * Overwrites the file with the current state of the taskList.
     * This method was partially inspired by code examples provided by W3Schools.
     * Asked ChatGPT for advice on how to incorporate polymorphism. It suggested toFileFormat method.
     * Solution below inspired by https://www.w3schools.com/java/java_files_create.asp
     * @param taskList The TaskList object containing the tasks to be saved.
     */
    public void saveTasks(TaskList taskList) {
        try {
            FileWriter myWriter = new FileWriter(filePath);

            // Loop through taskList to add each task to file
            for (int i = 0; i < taskList.getTasks().size(); i++) {
                myWriter.write(taskList.getTasks().get(i).toFileFormat());
            }
            myWriter.close();
            ui.showToUser("Tasks saved successfully!");
        } catch (IOException e) {
            ui.showToUser("Error saving tasks to file: " + e.getMessage());
        }
    }
}
