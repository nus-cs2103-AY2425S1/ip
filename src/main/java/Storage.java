import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws LoloException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                // Create the file if it doesn't exist
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs(); // Create the directories if they don't exist
                }
                file.createNewFile();
            } else {
                // Read the file content and load tasks
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                for (String line : lines) {
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
                            String by = parts[3];
                            task = new Deadline(description, by);
                            break;
                        case "E":
                            String from = parts[3];
                            String to = parts[4];
                            task = new Event(description, from, to);
                            break;
                        default:
                            throw new LoloException("Unknown task type in file.");
                    }

                    if (isDone) {
                        task.markAsDone();
                    }

                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new LoloException("Error loading tasks from file.");
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws LoloException {
        try {
            FileWriter writer = new FileWriter(filePath);

            for (Task task : tasks) {
                writer.write(task.toDataString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            throw new LoloException("Error saving tasks to file.");
        }
    }
}


