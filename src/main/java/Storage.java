import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void createFileIfNotExists() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs(); 
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                continue;
            }

            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            try {
                switch (taskType) {
                    case "T":
                        tasks.add(new Todo(description));
                        break;
                    case "D":
                        tasks.add(new Deadline(description, parts[3]));
                        break;
                    case "E":
                        tasks.add(new Event(description, parts[3], parts[4]));
                        break;
                }
                if (isDone) {
                    tasks.get(tasks.size() - 1).mark(); 
                }
            } catch (IndexOutOfBoundsException e) {
                // Ignore errors or handle appropriately
            }
        }
        scanner.close();
        return tasks;
    }

    public void save(TaskList tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks.getAll()) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
