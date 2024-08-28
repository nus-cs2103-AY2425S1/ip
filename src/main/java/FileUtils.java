import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class FileUtils {
    private static final String DATA_PATH = "./data.txt";

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
                System.out.println(s.nextLine());
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
            fw.write(taskList.getList());
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
