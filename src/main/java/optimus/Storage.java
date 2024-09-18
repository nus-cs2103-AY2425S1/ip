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
     * ChatGPT suggested how to shorten method
     *
     * @return The list of tasks loaded from the file.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File directory = new File("data");
        File file = new File(filePath);

        if (!directory.exists()) {
            directory.mkdir();
        }

        if (!file.exists()) {
            file.createNewFile();
            ui.showToUser("Created new data file optimus.txt");
            return taskList;
        }
        // Read tasks from the file
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    Task task = parseTaskFromLine(line);
                    taskList.add(task);
                } catch (OptimusException | ArrayIndexOutOfBoundsException e) {
                    // Suggested by ChatGPT
                    ui.showToUser("Error loading task: " + e.getMessage());
                }
            }
            ui.showToUser("Loaded tasks from file optimus.txt");
        }
        return taskList;
    }

    /**
     * Parses a task from a line of text.
     *
     * @param line The line of text representing the task.
     * @return The parsed Task object.
     * @throws OptimusException If there is an issue with parsing.
     */
    private Task parseTaskFromLine(String line) throws OptimusException {
        String[] parts = line.split("\\|");
        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");

        Task task;
        switch (taskType) {
            case "T":
                task = new Todo(parts[2].trim());
                break;
            case "D":
                task = new Deadline(parts[2].trim(), parts[3].trim());
                break;
            case "E":
                task = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
                break;
            default:
                throw new OptimusException("Invalid task type: " + taskType);
        }
        task.isDone = isDone;
        return task;
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
