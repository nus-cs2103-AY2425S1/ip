import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveData(ArrayList<Task> taskList) throws TickException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (Task task : taskList) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new TickException("An error occurred while saving data to file.");
        }
    }

    public ArrayList<Task> loadData() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(this.filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return taskList;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String taskString = scanner.nextLine();
            String[] parts = taskString.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task task;

            switch (taskType) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                continue;
            }
            if (isDone) {
                task.markAsDone();
            }
            taskList.add(task);
        }
        return taskList;
    }
}
