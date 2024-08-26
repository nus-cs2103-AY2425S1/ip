import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws IOException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTask(line);
                tasks.addTask(task);
            }
            scanner.close();
        }

        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks.getTasks()) {
            fw.write(task.toSave() + System.lineSeparator());
        }
        fw.close();
    }
}
