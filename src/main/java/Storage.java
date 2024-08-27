import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private final File taskFile;

    public Storage(String filePath) throws IOException {
        taskFile = new File(filePath);
        if (!taskFile.getParentFile().exists()) {
            taskFile.getParentFile().mkdirs();
        }
        if (!taskFile.exists()) {
            taskFile.createNewFile();
        }
    }

    public void addTask(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(taskFile, true);
        fileWriter.write(task.toStorageString() + System.lineSeparator());
        fileWriter.close();
    }

    public void updateTask(List<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(taskFile);
        for (Task task : tasks) {
            fileWriter.write(task.toStorageString() + System.lineSeparator());
        }
        fileWriter.close();
    }

    public List<Task> load() throws WaterfallException {
        try {
            Scanner scanner = new Scanner(taskFile);
            List<Task> tasks = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] strings = line.split(" \\| ");
                Task task;
                switch (strings[0]) {
                case "T":
                    task = new ToDo(strings[2]);
                    break;
                case "D":
                    task = new Deadline(strings[2], strings[3]);
                    break;
                case "E":
                    task = new Event(strings[2], strings[3], strings[4]);
                    break;
                default:
                    System.out.println("Unknown task type in database: " + line);
                    return null;
                }
                if (Objects.equals(strings[1], "1")) {
                    task.setDone(true);
                }
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new WaterfallException("File not found: " + taskFile);
        }
    }
}
