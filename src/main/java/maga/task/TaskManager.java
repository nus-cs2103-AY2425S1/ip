package maga.task;
import java.io.*;

public class TaskManager {
    private static final String FILE_PATH = "./data/maga.txt";

    public void saveTasks(TaskList taskList) {
        //create parent directory if it doesn't exist
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        //write to file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (int i = 0; i < taskList.getTaskCount(); i++) {
                writer.write(taskList.getTask(i).toString());
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("Error while saving tasks!");
        }
    }

    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(FILE_PATH);

        // check if file exists
        if (!file.exists()) {
            // handle file not existing scenario
            System.out.println("No save detected: Creating fresh tasklist!");
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.addTask(Task.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error while loading tasks!");
        }

        return tasks;
    }
}
