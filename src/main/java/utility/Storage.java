package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/** A class to store the Task data */
public class Storage {
    private final String storagePath;

    /**
     * Creates an instance of a Storage object.
     * @param storagePath Where data is stored.
     */
    public Storage(String storagePath) {
        assert storagePath != null && !storagePath.isEmpty() ;
        this.storagePath = storagePath;
    }

    /**
     * Saves a list of tasks to local hard disk.
     * @param tasks Is tasks to be saved.
     * @throws IOException When save fails.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        createFileIfItDoesNotExists();
        writeTasksToFile(tasks);
    }

    private void createFileIfItDoesNotExists() throws IOException {
        File file = new File(storagePath);
        if (!file.exists()) {
            File dir = new File("./data");
            boolean dirCreated = dir.mkdir();
            if (dirCreated && !file.createNewFile()) {
                throw new FileNotFoundException("Could not create file " + file.getAbsolutePath());
            }
        }
        assert (file.exists());
    }

    private void writeTasksToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(storagePath);
        for (Task task : tasks) {
            fw.write(task.getSaveFormat() + "\n");
        }
        fw.close();
    }

    /**
     * Loads saved tasks from local hard disk.
     * @return A list of tasks.
     * @throws IOException When load fails.
     */
    public ArrayList<Task> load() throws IOException {
        File saveFile = new File(storagePath);
        if (!saveFile.exists()) {
            return new ArrayList<>();
        }

        return Files.lines(Path.of(storagePath))
                .map(this::lineToTask)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Task lineToTask(String line) {
        return switch (line.charAt(0)) {
        case 'T' -> Todo.load(line);
        case 'D' -> DeadLine.load(line);
        case 'E' -> Event.load(line);
        default -> null;
        };
    }
}
