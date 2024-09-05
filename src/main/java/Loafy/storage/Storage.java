package loafy.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import loafy.loafyexception.LoafyException;
import loafy.task.Deadline;
import loafy.task.Event;
import loafy.task.Task;
import loafy.task.Todo;

public class Storage {
    private final String filePath;
    private boolean isWorking;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.isWorking = true;
    }

    public void writeToFile(String text) {
        if (this.isWorking) {
            try {
                FileWriter writer = new FileWriter(this.filePath);
                writer.write(text);
                writer.close();
            } catch (IOException exception) {
                System.out.println("Error: Changes failed to save to hard drive");
            }
        }
    }

    private List<String> load() throws LoafyException {
        try {
            new File(this.filePath).getParentFile().mkdirs();
            new File(this.filePath).createNewFile();
            Path path = Paths.get(this.filePath);
            return Files.readAllLines(path);
        } catch (SecurityException | IOException | NullPointerException exception) {
            this.isWorking = false;
            throw LoafyException.ofLoadingError();
        }
    }

    public ArrayList<Task> getList() throws LoafyException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            for (String line : this.load()) {
                String[] arr = line.split(",");
                boolean isDone = arr[1].equals("true");
                switch (arr[0]) {
                case "T":
                    Todo todo = new Todo(isDone, arr[2]);
                    tasks.add(todo);
                    break;
                case "D" :
                    Deadline deadline = new Deadline(isDone, arr[2], LocalDateTime.parse(arr[3]));
                    tasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(isDone, arr[2], LocalDateTime.parse(arr[3]), LocalDateTime.parse(arr[4]));
                    tasks.add(event);
                    break;
                default:
                    throw LoafyException.ofCorruptedList();
                }
            }
        } catch (IndexOutOfBoundsException | DateTimeParseException exception) {
            throw LoafyException.ofCorruptedList();
        }
        return tasks;
    }
}
