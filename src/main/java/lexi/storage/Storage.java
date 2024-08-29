package lexi.storage;

import lexi.exception.LexiException;
import lexi.task.Deadline;
import lexi.task.Event;
import lexi.task.Task;
import lexi.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filepath;
    private final File directory = new File("./data");
    private File data;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private ArrayList<Task> tasks = new ArrayList<>();
    public Storage(String filepath) {
        this.filepath = filepath;
    }
    public ArrayList<Task> load() throws LexiException {
        try {
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory if it doesn't exist
            }
            File file = new File(directory, "data.txt");
            file.createNewFile();
            data = file;
            Scanner contents = new Scanner(data);
            while (contents.hasNextLine()) {
                String[] parts = contents.nextLine().split(" !- ");
                String taskName = parts[2];
                if (parts[0].equals("T")) {
                    Todo task = new Todo(taskName);
                    if (parts[1].equals("1")) {
                        task.doTask();
                    }
                    tasks.add(task);
                } else if (parts[0].equals("D")) {
                    String deadline = parts[3];
                    LocalDateTime by = LocalDateTime.parse(deadline, this.inputFormatter);
                    Deadline task = new Deadline(taskName, by);
                    if (parts[1].equals("1")) {
                        task.doTask();
                    }
                    tasks.add(task);
                } else if (parts[0].equals("E")) {
                    String start = parts[3];
                    String end = parts[4];
                    LocalDateTime from = LocalDateTime.parse(start, this.inputFormatter);
                    LocalDateTime to = LocalDateTime.parse(end, this.inputFormatter);
                    Event task = new Event(taskName, from, to);
                    if (parts[1].equals("1")) {
                        task.doTask();
                    }
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new LexiException("");
        } catch (IOException e) {
            throw new LexiException("");
        }
        return tasks;
    }

    public void updateStorage(ArrayList<Task> tasks) throws LexiException {
        this.tasks = tasks;
        try {
            FileWriter fw = new FileWriter(data);
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
