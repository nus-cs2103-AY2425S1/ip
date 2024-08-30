import sigmabot.ui.UiComponent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ListReader extends Reader {
    @Override
    public void readFile(String filePath, UiComponent ui) {
        ui.printDialogue("Generic readFile method called");
    }
    private Map<String, Task> readFile(File file, UiComponent ui) {
        Map<String, Task> taskMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 3); // Split the line into three parts
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String description = parts[1].trim();
                    boolean isDone = parts[2].trim().equalsIgnoreCase("true");
                    Task task = new Task(name, description) {};
                    if (isDone) {
                        task.markDone();
                    }
                    taskMap.put(name, task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskMap;
    }

    public Map<String, Map<String, Task>> readDirectory(String directoryPath, UiComponent ui) {
        Map<String, Map<String, Task>> directoryMap = new HashMap<>();
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
            if (files != null) {
                for (File file : files) {
                    Map<String, Task> taskMap = readFile(file, ui);
                    directoryMap.put(file.getName(), taskMap);
                }
            }
        }
        return directoryMap;
    }
}
