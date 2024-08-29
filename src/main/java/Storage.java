import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) throws MorganaException {
        this.filePath = filePath;
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new MorganaException("Failed to initialize storage: %s".formatted(e.getMessage()));
        }
    }

    public List<Task> load() throws MorganaException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                Task task = Parser.parseTask(sc.nextLine());
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new MorganaException("Failed to load tasks from file: %s".formatted(e.getMessage()));
        }
        return tasks;
    }

    public void save(TaskList tasks) throws MorganaException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < tasks.size(); i++) {
                writer.write("%s%n".formatted(tasks.get(i).toFileFormat()));
            }
        } catch (IOException e) {
            throw new MorganaException("Failed to save tasks to file: %s".formatted(e.getMessage()));
        }
    }
}
