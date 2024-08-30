import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    Task task = null;
                    try {
                        switch (parts[0]) {
                            case "T":
                                task = new Todo(parts[2]);
                                break;
                            case "D":
                                task = new Deadline(parts[2], parts[3]);
                                break;
                            case "E":
                                task = new Event(parts[2], parts[3], parts[4]);
                                break;
                        }
                        if (task != null && parts[1].equals("1")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                    } catch (YapperException e) {
                        System.out.println("Error loading task from file: " + e.getMessage());
                        // Continue loading other tasks even if this one fails
                    }
                }
            }
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        }
    }
}
