import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskFileManager {
    private final File file;
    public TaskFileManager(Path path) {
        file = path.toAbsolutePath()
                .normalize()
                .toFile();
    }

    public void saveTasksToFile(Task[] tasks) throws IOException {
        createFileIfNotExists();

        FileWriter writer = new FileWriter(file);
        String result = toSaveFormat(tasks);
        writer.write(result);
        writer.close();
    }

    public Task[] readTasksFromFile() throws IOException {
        if (!file.exists()) {
            return new Task[0];
        }

        Scanner sc = new Scanner(new FileReader(file));
        sc.useDelimiter("\n");

        ArrayList<Task> tasks = new ArrayList<>();
        while(sc.hasNext()) {
            try {
                String taskString = sc.nextLine();
                tasks.add(TaskFactory.createTask(taskString, InputSource.FILE));
            } catch (IllegalArgumentException | DateTimeParseException e) {
                // Task contains faulty information in file, just skip
            }
        }

        sc.close();

        // convert to task objects
        return tasks.toArray(new Task[0]);
    }

    private String toSaveFormat(Task[] tasks) {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append(task.toSaveFormat());
            result.append("\n");
        }
        return result.toString();
    }

    private void createFileIfNotExists() throws IOException {
        if (file.exists()) {
            return;
        }

        boolean parentDirectoryCreated = file.getParentFile().mkdirs();
        boolean fileCreated = file.createNewFile();

        if (!parentDirectoryCreated) {
            throw new IOException("An error occurred when trying to initialise the file directory " + file.getPath());
        }

        if (!fileCreated) {
            throw new IOException("An error occurred when trying to initialise " + file.getName());
        }
    }
}
