import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private String path;
    public Storage(String path) {
        this.path = path;
    }

    public TaskList load() throws IOException {
        TaskList storedTasks = new TaskList();
        File file = new File(this.path);
        if (!file.exists()) {
            return storedTasks; // No file exists, start with an empty task list
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] taskData = line.split(" \\| ");
            Task task;
            switch (taskData[0]) {
            case "T":
                task = new Todo(taskData[2]);
                break;
            case "D":
                task = new Deadline(taskData[2], taskData[3]);
                break;
            case "E":
                String description = taskData[2];
                String[] fromTo = taskData[3].split(" to ");
                String from = fromTo[0];
                String to = fromTo[1];
                task = new Event(description, from, to);
                break;
            default:
                continue; // Skip unknown task types
            }
            if (taskData[1].equals("1")) {
                task.markAsDone();
            }
            storedTasks.addTask(task);
        }
        scanner.close();
        return storedTasks;
    }

    public void save(TaskList storedTasks) throws IOException {
        File f = new File(this.path);
        f.getParentFile().mkdirs(); // create directories if they don't exist
        FileWriter fw = new FileWriter(this.path);
        for (Task task : storedTasks.getAllTasks()) {
            fw.write(task.toFileFormat() + "\n");
        }
        fw.close();
    }
}
