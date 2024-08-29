import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String taskData = scanner.nextLine();
                    tasks.add(parseTask(taskData));
                }
                scanner.close();
            } else {
                createFile();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Jartaloon! " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(List<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Jartaloon! " + e.getMessage());
        }
    }

    private Task parseTask(String taskData) throws JardException {
        String[] parts = taskData.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case "E":
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
            default:
                throw new JardException("Jartaloon! " + taskData);
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    private void createFile() {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Jartaloon! " + e.getMessage());
        }
    }
}
