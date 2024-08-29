import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH = "./data/kobee.txt";

    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        try {
            if (!file.exists()) {
                new File("./data").mkdirs(); // Create the directory if it doesn't exist
                file.createNewFile(); // Create the file if it doesn't exist
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    String taskType = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String name = parts[2];

                    switch (taskType) {
                        case "T":
                            Task todo = new Todo(name);
                            if (isDone) todo.markAsDone();
                            tasks.add(todo);
                            break;
                        case "D":
                            String by = parts[3];
                            Task deadline = new Deadline(name, by);
                            if (isDone) deadline.markAsDone();
                            tasks.add(deadline);
                            break;
                        case "E":
                            String from = parts[3];
                            String to = parts[4];
                            Task event = new Event(name, from, to);
                            if (isDone) event.markAsDone();
                            tasks.add(event);
                            break;
                        default:
                            throw new IOException("Invalid task type in file: " + taskType);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    public static void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
