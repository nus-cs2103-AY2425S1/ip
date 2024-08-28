package skywalker.storage;
import skywalker.parser.Parser;
import skywalker.task.TaskList;
import skywalker.task.Task;

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

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTaskFromFileString(line);
                tasks.add(task);
            }
            scanner.close();
        }
        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks.getTasks()) {
            writer.write(Parser.taskToFileString(task) + "\n");
        }
        writer.close();
    }
}
