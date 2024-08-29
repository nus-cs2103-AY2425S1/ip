import bao.task.Task;

import java.io.*;
import java.util.ArrayList;
public class Storage {
    private String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFile() throws IOException, FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            Task task = Task.fromString(line);
            tasks.add(task);
        }
        reader.close();
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        PrintWriter writer = new PrintWriter(file);
        for (Task task : tasks) {
            writer.println(task.toFileString());
        }
        writer.close();
    }
}
