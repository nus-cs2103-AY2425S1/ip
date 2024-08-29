package storage;

import tasks.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {
    private final String filePath;
    private final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
    private final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //Load tasks from the file
    public TaskList load() throws IOException {
        TaskList tasks = new TaskList();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while((line = reader.readLine()) != null) {
            Task task = parseTask(line);
            tasks.addTask(task);
        }
        reader.close();
        return tasks;
    }

    //Method to parse a line into a Task object
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            System.out.print("File corrupted");
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch(type) {
            case "T":
                return new ToDoTask(description, isDone);
            case "D":
                if (parts.length < 4) {
                    System.out.print("File corrupted");
                    return null;
                }
                String deadlineStr = parts[3];
                // Parse the string in the current inputFormat
                LocalDateTime deadline = LocalDateTime.parse(deadlineStr, inputFormat);
                // Convert the string into the desired format for me to create a new Task.
                return new DeadlineTask(description, isDone, deadline.format(outputFormat));
            case "E":
                if (parts.length < 5) {
                    System.out.print("File corrupted");
                    return null;
                }
                String startStr = parts[3];
                String endStr = parts[4];
                // Parse the string in the current inputFormat
                LocalDateTime start = LocalDateTime.parse(startStr, inputFormat);
                LocalDateTime end = LocalDateTime.parse(endStr, inputFormat);
                return new EventTask(description, isDone, start.format(outputFormat).trim(), end.format(outputFormat).trim());
            default:
                return null;
        }
    }

    public void saveTasksToFile(TaskList tasks) throws IOException {
        try {
            tasks.write(this.filePath);
        } catch (FileNotFoundException e) {
            System.out.print("File cannot be found");
        }
    }


}
