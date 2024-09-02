package bottle;

import bottle.task.Task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;
    private Parser parser;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.parser = new Parser();
    }
    private void handleMissingFile() {
        Path path = Paths.get(filePath);
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (IOException e){
            System.out.println("Error occurred creating file");
        }
    }
    public ArrayList<Task> loadTasks() {
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        if (!file.exists()) {
            handleMissingFile();
        } else {
            try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                for (String line : lines) {
                    String[] parts = line.split("\\|");
                    Task task;
                    task = parser.parseTask(line);
                    taskList.add(task);
                }
            } catch (IOException e) {
                System.out.println("Error reading file");
            }
        }
        return taskList;
    }
    public void saveTasks(ArrayList<Task> taskList) {
        File file = new File(filePath);
        if (!file.exists()) {
            handleMissingFile();
        }
        try {
            FileWriter fileWriter =  new FileWriter(file);
            for (Task task : taskList) {
                fileWriter.write(task.toSaveFormat() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }
}
