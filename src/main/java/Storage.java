import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class Storage {

    private String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(task.toFileFormat() + System.lineSeparator());
        }
        writer.close();

    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            File parentFile = file.getParentFile();
            parentFile.mkdirs();
            return tasks;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] taskData = line.split(" \\| ");
            Task task = createTaskFromData(taskData);
            tasks.add(task);
        }
        scanner.close();
        return tasks;

    }

    private Task createTaskFromData(String[] taskData) {
        String type = taskData[1];
        boolean isDone = taskData[2].equals("1");
        String description = taskData[2];

        Task task = null;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String by = taskData[3];
            task = new Deadline(description, by);
        case "E":
            String from = taskData[3];
            String to = taskData[4];
            task = new Event(description, from, to);
            break;
        }
        if (isDone) {
            task.markDone();
        }
        return task;
    }

}
