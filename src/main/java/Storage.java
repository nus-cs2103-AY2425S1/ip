import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "./data/tasks.txt";

    public static void saveTasks(ArrayList<Task> taskList) {
        File file = new File(FILE_PATH);
        File directory = file.getParentFile();
        if (!directory.exists() && directory != null) {
            directory.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : taskList) {
                writer.write(task.getTaskTypeIcon() + " | " 
                            + (task.isDone ? "1" : "0") + " | " 
                            + task.getSaveFormat() + "\n");
            }
        } catch (IOException e) {
            Ui.error(e);
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                Task task = null;

                switch (taskType) {
                    case "[T]":
                        task = new Todo(parts[2]);
                        break;
                    case "[D]":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "[E]":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                }

                if (task != null) {
                    if (isDone) {
                        task.mark();
                    }
                    taskList.add(task);
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            Ui.initialise("No saved tasks found. Starting fresh...");
            return new ArrayList<>();
        } catch (IOException e) {
            Ui.initialise("Unable to access data. Starting fresh...");
            return new ArrayList<>();
        }
    }
}
