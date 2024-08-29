package krona.storage;

import krona.task.Task;
import krona.task.TaskList;
import krona.exception.KronaException;
import krona.parser.Parser;
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

    public ArrayList<Task> load() throws KronaException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = Parser.parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            throw new KronaException("Error reading file");
        }
        return tasks;
    }

    public void save(TaskList tasks) throws KronaException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                writer.write(Parser.taskToString(task) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new KronaException("Error saving to file.");
        }
    }
}
