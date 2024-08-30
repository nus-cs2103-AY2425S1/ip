package lexi.storage;

import lexi.exception.LexiException;
import lexi.task.Deadline;
import lexi.task.Event;
import lexi.task.Task;
import lexi.task.Todo;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {
    private File dataFile;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private ArrayList<Task> tasks = new ArrayList<>();

    public Storage(String filepath) throws LexiException{
        this.dataFile = new File(filepath);
        // Ensure the directory exists
        File directory = dataFile.getParentFile();
        if (directory != null && !directory.exists()) {
            if (!directory.mkdirs()) {
                throw new LexiException("Failed to create directory: " + directory.getAbsolutePath());
            }
        }
        // Ensure the file exists
        try {
            if (!dataFile.exists() && !dataFile.createNewFile()) {
                throw new LexiException("Failed to create file: " + filepath);
            }
        } catch (IOException e) {
            throw new LexiException("Failed to create file: " + filepath);
        }
    }
    public ArrayList<Task> load() throws LexiException {
        try {
            Scanner contents = new Scanner(dataFile);
            while (contents.hasNextLine()) {
                String[] parts = contents.nextLine().split(" !- ");
                String taskName = parts[2];
                if (parts[0].equals("T")) {
                    Todo task = new Todo(taskName);
                    if (parts[1].equals("1")) {
                        task.setDone(true);
                    }
                    tasks.add(task);
                } else if (parts[0].equals("D")) {
                    String deadline = parts[3];
                    LocalDateTime by = LocalDateTime.parse(deadline, this.inputFormatter);
                    Deadline task = new Deadline(taskName, by);
                    if (parts[1].equals("1")) {
                        task.setDone(true);
                    }
                    tasks.add(task);
                } else if (parts[0].equals("E")) {
                    String start = parts[3];
                    String end = parts[4];
                    LocalDateTime from = LocalDateTime.parse(start, this.inputFormatter);
                    LocalDateTime to = LocalDateTime.parse(end, this.inputFormatter);
                    Event task = new Event(taskName, from, to);
                    if (parts[1].equals("1")) {
                        task.setDone(true);
                    }
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new LexiException("File not found:" + dataFile.getAbsolutePath());
        }
        return tasks;
    }
    public void updateStorage(ArrayList<Task> tasks) throws LexiException {
        this.tasks = tasks;
        try {
            FileWriter fw = new FileWriter(dataFile);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            for (Task task: tasks) {
                char taskType = task.toString().split(" ")[0].charAt(1);
                if (taskType == 'T') {
                    fw.write(String.format("%c !- %d !- %s", taskType,
                            task.getIsDone() ? 1 : 0, task.getTaskName()) + System.lineSeparator());
                } else if (taskType == 'D') {
                    Deadline obj = (Deadline) task;
                    fw.write(String.format("%c !- %d !- %s !- %s", taskType,
                            obj.getIsDone() ? 1 : 0, obj.getTaskName(), obj.getBy().format(formatter))
                            + System.lineSeparator());
                } else if (taskType == 'E') {
                    Event obj = (Event) task;
                    fw.write(String.format("%c !- %d !- %s !- %s !- %s", taskType,
                            obj.getIsDone() ? 1 : 0, obj.getTaskName(),
                            obj.getFrom().format(formatter),
                            obj.getTo().format(formatter)) + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new LexiException("Something went wrong with writing to data\n"
                    + "Please contact your system administrator!\n"
                    + "Error Message:\n"
                    + e.getMessage());
        }

    }
}
