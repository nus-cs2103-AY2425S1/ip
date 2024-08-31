package muller.storage;

import muller.task.Task;
import muller.task.TaskList;
import muller.command.MullerException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws MullerException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return list;  // No tasks to load, return empty list
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    throw new MullerException("File is corrupted at line " + lineNumber + ".");
                }
                Task task = parseTask(parts);
                list.add(task);
                lineNumber++;
            }
            reader.close();
        } catch (IOException e) {
            throw new MullerException("Error loading tasks: " + e.getMessage());
        }
        return list;
    }

    public void save(TaskList tasks) throws MullerException {
        try {
            File file = new File(filePath);
            File directory = new File(file.getParent());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new MullerException("Error saving tasks: " + e.getMessage());
        }
    }

    private Task parseTask(String[] parts) throws MullerException {
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String name = parts[2];
        Task task = new Task(name);
        task.setType(type);
        task.markAsDone(isDone);

        if (type.equals("[D]") && parts.length >= 4) {
            LocalDate date = LocalDate.parse(parts[3], Task.INPUT_DATE_FORMATTER);
            task.setDate(date);
        } else if (type.equals("[E]") && parts.length >= 5) {
            LocalDate startDate = LocalDate.parse(parts[3], Task.INPUT_DATE_FORMATTER);
            LocalDate endDate = LocalDate.parse(parts[4], Task.INPUT_DATE_FORMATTER);
            task.setDateRange(startDate, endDate);
        }
        return task;
    }
}
