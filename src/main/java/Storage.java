import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Save tasks to file
    public void saveTasks(ArrayList<Task> tasks, Ui ui) {
        try {
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toSaveString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            ui.showError("Unable to save tasks.");
        }
    }

    // Load tasks from file
    public ArrayList<Task> loadTasks(Ui ui) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;  // No file yet, nothing to load
        }

        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(" \\| ");
            switch (parts[0]) {
                case "T":
                    Todo todo = new Todo(parts[2]);
                    if (parts[1].equals("1")) {
                        todo.markAsDone();
                    }
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1")) {
                        event.markAsDone();
                    }
                    tasks.add(event);
                    break;
                default:
                    throw new IOException("Corrupted task format");
            }
        }
        fileScanner.close();
        return tasks;
    }
}
