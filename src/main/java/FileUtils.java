import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class FileUtils {
    private static final String DATA_PATH = "./data.txt";

    private static void processTaskString(String taskString, TaskList taskList) {
        try {
            String[] taskParts = taskString.split("\\|");
            String taskType = taskParts[0];
            boolean isDone = taskParts[1].equals("1");
            String taskDescription = taskParts[2];
            Task task;
            switch (taskType) {
            case "T":
                task = new Todo(taskDescription);
                if (isDone) {
                    task.markAsDone();
                }
                taskList.addItem(task);
                break;
            case "D":
                task = new Deadline(taskDescription, taskParts[3]);
                if (isDone) {
                    task.markAsDone();
                }
                taskList.addItem(task);
                break;
            case "E":
                task = new Event(taskDescription, taskParts[3], taskParts[4]);
                if (isDone) {
                    task.markAsDone();
                }
                taskList.addItem(task);
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error loading task: The saved task is in an invalid format.");
        }
    }

    private static void createFileIfNotExists() {
        // This method is created with use of ChatGPT

        // Create a File object for the file
        File file = new File(DATA_PATH);
        try {
            // Check if the file exists, if not, create it
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Failed to create the file");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred." + e.getMessage());
        }
    }

    public static TaskList loadTasks() {
        // Load tasks from file
        createFileIfNotExists();
        TaskList taskList = new TaskList();
        try {
            File f = new File(FileUtils.DATA_PATH); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                processTaskString(s.nextLine(), taskList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return taskList;
    }

    public static void saveTasks(TaskList taskList) {
        // Save tasks to file
        try {
            FileWriter fw = new FileWriter(FileUtils.DATA_PATH);
            fw.write(taskList.getSaveFormatList());
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
