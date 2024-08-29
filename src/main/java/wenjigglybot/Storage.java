package wenjigglybot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;

    }

    public void load(TaskList tasks) throws WenJigglyBotException {
        File file = new File(filePath);

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("No saved tasks found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Parser.parseTask(line);
                if (task != null) {
                    tasks.addTask(task);
                }
            }
            System.out.println("Tasks loaded from " + file.getPath());
        } catch (IOException e) {
            throw new WenJigglyBotException("An error occurred while loading tasks from file: " + e.getMessage());
        }
    }

    public static void saveTasksToFile(TaskList tasks) {
        String tasksString = tasksToString(tasks);
        // Create a File object for the directory
        File directory = new File("./data");

        // If the directory doesn't exist, create it
        if (!directory.exists()) {
            boolean mkdirs = directory.mkdirs();// This will create the directory and any necessary but nonexistent parent directories
            if (!mkdirs) {
                System.out.println("Failed to make directory!");
            }
        }
        try (FileWriter writer = new FileWriter("./data/data.txt")) {
            writer.write(tasksString);
            System.out.println("Tasks saved to " + "data.txt");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    private static String tasksToString(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }


}