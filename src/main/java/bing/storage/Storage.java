package bing.storage;

import bing.task.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            return tasks;
        }

        Scanner scanner = new Scanner(file);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            String status = parts[1];
            String info = parts[2];

            Task task;

            switch (taskType) {
                case "T":
                    task = new ToDo(info);
                    break;
                case "D":
                    LocalDateTime deadline = LocalDateTime.parse(parts[3], formatter);
                    task = new Deadline(info, deadline);
                    break;
                case "E":
                    LocalDateTime from = LocalDateTime.parse(parts[3], formatter);
                    LocalDateTime to = LocalDateTime.parse(parts[4], formatter);
                    task = new Event(info, from, to);
                    break;
                default:
                    continue; // Skip lines with invalid task type
            }

            task.setStatus(status.equals("1") ? TaskStatus.DONE : TaskStatus.UNDONE);
            tasks.add(task);
        }

        scanner.close();
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.toFileFormat(formatter) + "\n");
        }
        writer.close();
    }
}
