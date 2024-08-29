import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH = "./data/kobee.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

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
                            try {
                                LocalDateTime by = LocalDateTime.parse(parts[3], FORMATTER);
                                Task deadline = new Deadline(name, by);
                                if (isDone) deadline.markAsDone();
                                tasks.add(deadline);
                            } catch (DateTimeParseException e) {
                                System.out.println("Error parsing date for deadline task: " + e.getMessage());
                            }
                            break;
                        case "E":
                            try {
                                LocalDateTime from = LocalDateTime.parse(parts[3], FORMATTER);
                                LocalDateTime to = LocalDateTime.parse(parts[4], FORMATTER);
                                Task event = new Event(name, from, to);
                                if (isDone) event.markAsDone();
                                tasks.add(event);
                            } catch (DateTimeParseException e) {
                                System.out.println("Error parsing date for event task: " + e.getMessage());
                            }
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
