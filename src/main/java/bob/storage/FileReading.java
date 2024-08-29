package bob.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import bob.data.TaskList;
import bob.tasks.DeadlineTask;
import bob.tasks.EventTask;
import bob.tasks.Task;
import bob.tasks.TodoTask;

import java.time.LocalDateTime;
import java.util.Scanner;

public class FileReading extends Storage {
    private static final String SEPARATOR = " \\| ";

    public FileReading(String filePath) {
        super(filePath);
    }

    protected static void createDirectory(String dirName) {
        File directory = new File(dirName);

        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    protected static void createFile(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile(); // Creates the file if it does not exist
        }
    }

    protected static TaskList loadTasks(String filePath) throws FileNotFoundException {
        TaskList list = new TaskList();
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String[] parts = s.nextLine().split(SEPARATOR);

            switch (parts[0]) {
                case "T" -> {
                    Task todo = new TodoTask(parts[2]);
                    todo.isDone = isDone(Integer.parseInt(parts[1]));
                    list.add(todo);
                }
                case "D" -> {
                    LocalDateTime by = LocalDateTime.parse(parts[3]);
                    Task deadline = new DeadlineTask(parts[2], by);
                    deadline.isDone = isDone(Integer.parseInt(parts[1]));
                    list.add(deadline);
                }
                case "E" -> {
                    LocalDateTime from = LocalDateTime.parse(parts[3]);
                    LocalDateTime to = LocalDateTime.parse(parts[4]);
                    Task event = new EventTask(parts[2], from, to);
                    event.isDone = isDone(Integer.parseInt(parts[1]));
                    list.add(event);
                }
            }
        }
        return list;
    }

    protected static boolean isDone(int i) {
        return i == 1;
    }
}
