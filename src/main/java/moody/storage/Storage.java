package moody.storage;

import moody.tasks.Deadline;
import moody.tasks.Event;
import moody.tasks.Task;
import moody.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isTaskDone = parts[1].equals("1");
            Task task;

            switch (taskType) {
            case "T":
                task = new Todo(parts[2].trim());
                break;
            case "D":
                task = new Deadline(parts[2].trim(), LocalDateTime.parse(parts[3].trim(), INPUT_FORMATTER));
                break;
            case "E":
                task = new Event(parts[2].trim(), LocalDateTime.parse(parts[3].trim(), INPUT_FORMATTER),
                        LocalDateTime.parse(parts[4].trim(), INPUT_FORMATTER));
                break;
            default:
                continue;
            }

            if (isTaskDone) {
                task.markAsDone();
            }

            tasks.add(task);
        }

        scanner.close();
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        File directory = new File(filePath).getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        FileWriter fileWriter = new FileWriter(filePath);

        for (Task task : tasks) {
            fileWriter.write(task.toFileFormat() + System.lineSeparator());
        }
        fileWriter.close();
    }
}
