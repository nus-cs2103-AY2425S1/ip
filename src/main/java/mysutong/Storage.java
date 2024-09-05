package mysutong;

import mysutong.Deadline;
import mysutong.Event;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            switch (taskType) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    LocalDateTime deadlineDate = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                    task = new Deadline(description, deadlineDate);
                    break;
                case "E":
                    LocalDateTime eventStart = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                    LocalDateTime eventEnd = LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                    task = new Event(description, eventStart, eventEnd);
                    break;
                default:
                    throw new IllegalStateException("Unknown task type: " + taskType);
            }

            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }

        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks.getTasks()) {
            writer.write(task.toFileString());
            writer.newLine();
        }
        writer.close();
    }
}
