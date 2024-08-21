import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        
        // If the file doesn't exist, create the directory and the file
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Create the directory if it does not exist
            file.createNewFile(); // Create the file if it does not exist
            return tasks; // Return an empty list if no file exists
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            if (taskType.equals("T")) {
                task = new ToDo(description);
            } else if (taskType.equals("D")) {
                String by = parts[3];
                task = new Deadline(description, by);
            } else if (taskType.equals("E")) {
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
            } else {
                continue;
            }

            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.toSaveFormat() + System.lineSeparator());
        }
        writer.close();
    }
}