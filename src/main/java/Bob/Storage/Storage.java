package bob.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import bob.Bob;
import bob.tasks.Deadline;
import bob.tasks.Event;
import bob.tasks.Task;
import bob.tasks.Todo;
import bob.exception.BobException;


public class Storage {
    private String filePath;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws BobException {
        try {
            File file = initializeFile();
            Scanner scanner = new Scanner(file);
            return loadCurrentTasks(scanner);
        } catch (IOException e) {
            throw new BobException("Failed to load tasks from file");
        }
    }

    private File initializeFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }

    private ArrayList<Task> loadCurrentTasks(Scanner scanner) throws BobException {
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Task task = parseTask(line);
            tasks.add(task);
        }
        return tasks;
    }

    private Task parseTask(String line) throws BobException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        Task task = createTaskFromParts(taskType, parts);
        if (isDone) {
            task.mark();
        }
        return task;
    }

    private Task createTaskFromParts(String taskType, String[] parts) throws BobException {
        switch (taskType) {
        case "T":
            return new Todo(parts[2]);
        case "D":
            LocalDateTime deadlineDateTime = parseDeadline(parts[3]);
            return new Deadline(parts[2], deadlineDateTime);
        case "E":
            return new Event(parts[2], parts[3], parts[4]);
        default:
            throw new BobException("Unknown task type");
        }
    }

    private LocalDateTime parseDeadline(String dateString) throws BobException {
        try {
            return LocalDateTime.parse(dateString, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                LocalDate deadlineDate = LocalDate.parse(dateString, DATE_FORMATTER);
                return deadlineDate.atStartOfDay();
            } catch (DateTimeParseException ex) {
                throw new BobException("Invalid date format in file!");
            }
        }
    }

    public void save(ArrayList<Task> tasks) throws BobException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.fileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new BobException("Failed to save tasks to file");
        }
    }
}
