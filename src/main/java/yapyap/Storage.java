package yapyap;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws YapperBotException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Path datafilePath = Paths.get(filePath);
            if (Files.exists(datafilePath)) {
                BufferedReader reader = Files.newBufferedReader(datafilePath);
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] taskInfo = line.split(" \\| ");
                    String type = taskInfo[0];
                    boolean isDone = taskInfo[1].equals("1");
                    String description = taskInfo[2];

                    switch (type) {
                    case "T":
                        tasks.add(new Todo(description, isDone));
                        break;
                    case "D":
                        String by = taskInfo[3];
                        tasks.add(new Deadline(description, by, isDone));
                        break;
                    case "E":
                        String from = taskInfo[3];
                        String to = taskInfo[4];
                        tasks.add(new Event(description, from, to, isDone));
                        break;
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            throw new YapperBotException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            Path dataFilePath = Paths.get(filePath);
            Files.createDirectories(dataFilePath.getParent());
            FileWriter fw = new FileWriter(dataFilePath.toString());

            for (Task task : tasks) {
                fw.write(task.toSaveFormat() + System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("Unable to save task(s) to file, error occurred: " + e.getMessage());
        }
    }
}

