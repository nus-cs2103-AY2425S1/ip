import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" \\| ");
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];
                    Task task;

                    switch (type) {
                        case "T":
                            task = new ToDo(description);
                            break;
                        case "D":
                            task = new Deadline(description, parts[3]);
                            break;
                        case "E":
                            task = new Event(description, parts[3], parts[4]);
                            break;
                        default:
                            throw new ChatBuddyException("Invalid task type in file.");
                    }

                    if (isDone) {
                        task.markAsDone();
                    }

                    tasks.add(task);
                }
                scanner.close();
            } else {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException | ChatBuddyException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }
}
