import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskFileManager {
    private final File file;
    public TaskFileManager(Path path) {
        file = path.toAbsolutePath()
                .normalize()
                .toFile();
    }

    public void saveTasks(Task[] tasks) throws IOException {
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

        ArrayList<String> taskStrings = new ArrayList<>();
        while(sc.hasNext()) {
            taskStrings.add(sc.nextLine());
        }

        // convert to task objects
        return new Task[0];
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
