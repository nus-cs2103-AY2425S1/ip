package gale;
import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            Task task = fileStringToTask(line);
            if (task != null) {
                taskList.add(task);
            }
        }
        reader.close();
        return taskList;
    }

    public void saveTasks(ArrayList<Task> taskList) throws IOException {
        FileWriter writer = new FileWriter(this.filePath);
        for (Task task : taskList) {
            writer.write(task.toFileString());
            writer.write("\n");
        }
        writer.close();
    }

    public Task fileStringToTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }
        Task task;
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String desc = parts[2];
        try {
            switch (type) {
            case "T":
                task = new ToDo(desc);
                break;
            case "D":
                if (parts.length < 4) {
                    return null;
                }
                task = new Deadline(desc, parts[3]);
                break;
            case "E":
                if (parts.length < 5) {
                    return null;
                }
                task = new Event(desc, parts[3], parts[4]);
                break;
            default:
                return null;
            }
        } catch (DateTimeParseException e) {
            return null;
        }
        if (isDone) task.markAsDone();
        return task;
    }
}
