package sigmabot.util;

import sigmabot.task.Task;
import sigmabot.ui.UiComponent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.io.BufferedWriter;

public class ListMapWriter extends Writer {
    /**
     * Writes a list of lists of tasks to static file(s).
     * @param map Dialogue instance map of lists.
     * @param directoryPath Directory path that files to be written in.
     * @param ui Dialogue instance ui element.
     */
    public void writeMapToFile(Map<String, Map<String, Task>> map, String directoryPath, UiComponent ui) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        for (Map.Entry<String, Map<String, Task>> outerEntry : map.entrySet()) {
            String outerKey = outerEntry.getKey();
            Map<String, Task> innerMap = outerEntry.getValue();
            File file = new File(directoryPath + "/" + outerKey + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Map.Entry<String, Task> innerEntry : innerMap.entrySet()) {
                    String taskName = innerEntry.getKey();
                    Task task = innerEntry.getValue();
                    writer.write(taskName + ": " + task.getDescription() + ": " + task.isDone());
                    writer.newLine();
                }
            } catch (IOException e) {
                ui.printDialogue("Error writing to file: " + file.getName());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void write(String filePath, UiComponent ui) {
        System.out.println("default write function, not to be called.");
    }
}
