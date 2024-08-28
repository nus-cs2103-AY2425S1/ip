
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        final File file = new File(filePath);
        if (!file.exists()) {
            return taskList;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");

            Task task;
            switch (type) {
                case "T":
                    task = new ToDos(parts[2].trim());
                    break;
                case "D":
                    LocalDate date = LocalDate.parse(parts[3].trim());
                    task = new Deadlines(parts[2].trim(), date);
                    break;
                case "E":
                    String[] startEnd = parts[3].split("to");
                    LocalDate start = LocalDate.parse(startEnd[0].trim());
                    LocalDate end = LocalDate.parse(startEnd[1].trim());
                    task = new Event(parts[2].trim(), start, end);
                    break;
                default:
                    continue;
            }

            if (isDone) {
                task.markAsDone();
            }
            taskList.add(task);
        }
        br.close();
        return taskList;
    }

    public void saveTasks(TaskList taskList) {
        FileWriter fw = null;
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            fw = new FileWriter(filePath);
            ArrayList<Task> tasks = taskList.getTasks();
            for (Task task : tasks) {
                String taskString = task.toFileString();
                fw.write(taskString + "\n");
            }
        } catch (IOException ex) {
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
            }
        }
    }

}
