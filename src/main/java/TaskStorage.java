import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TaskStorage {
    private String filePath;

    public TaskStorage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // Return an empty list if the file does not exist
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                    case "T":
                        task = new ToDo(parts[2]);
                        if (parts[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    case "D":
                        task = new Deadline(parts[2], parts[3]);
                        if (parts[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        if (parts[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    default:
                        throw new IOException("Corrupted data file: Unexpected task type.");
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
