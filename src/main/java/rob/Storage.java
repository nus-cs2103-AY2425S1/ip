package rob;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Stores previous tasks added by user.
 */
public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from a file and adds them to the provided task list.
     * If the file does not exist, no tasks are added.
     *
     * @param tasks The task list to add loaded tasks to.
     * @return The updated list of tasks.
     * @throws IOException If an error occurs while reading the file.
     */
    public List<Task> loadTasks(TaskList tasks) throws IOException {
        File file = new File(this.filePath);
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ", 5);

                String type = parts[0].trim();
                String status = parts[1].trim();
                String desc = parts[2].trim();
                String firstLimiter = (parts.length > 3) ? parts[3].trim() : "";
                String secondLimiter = (parts.length > 4) ? parts[4].trim() : "";

                switch (type) {
                case "[T]" -> {
                    Todo todoTask = new Todo(desc);
                    if (status.equals("[X]")) {
                        todoTask.markAsDone();
                    }
                    tasks.getTasks().add(todoTask);
                }
                case "[D]" -> {
                    Deadline deadlineTask = new Deadline(desc, firstLimiter);
                    if (status.equals("[X]")) {
                        deadlineTask.markAsDone();
                    }
                    tasks.getTasks().add(deadlineTask);
                }
                case "[E]" -> {
                    Event eventTask = new Event(desc, firstLimiter, secondLimiter);
                    if (status.equals("[X]")) {
                        eventTask.markAsDone();
                    }
                    tasks.getTasks().add(eventTask);
                }
                default -> throw new IllegalStateException("Unexpected value: " + type);
                }
            }
            scanner.close();
        }
        return tasks.getTasks();
    }

    /**
     * Saves the tasks from the provided task list to a file.
     * Ensures that the necessary directory is created if they do not exist.
     *
     * @param tasks The task list containing tasks to be saved.
     */
    public void saveTasks(TaskList tasks) {
        List<Task> taskList = tasks.getTasks();
        try {
            Files.createDirectories(Paths.get("./data"));
            FileWriter writer = new FileWriter(this.filePath);
            for (Task task : taskList) {
                writer.write(task.toSaveString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }
}
