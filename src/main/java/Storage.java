import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static String filePath ;

    public Storage(String fp) {
        filePath = fp;
    }

    private static void ensureDataDirectoryExists() throws BobException {
        File dataDir = new File(filePath).getParentFile();
        if (dataDir != null && !dataDir.exists()) {
            if (!dataDir.mkdir()) {
                throw new BobException("Failed to create directory: " + dataDir.getAbsolutePath());
            }
        }
    }

    public List<Task> load() throws BobException {
        try {
            List<Task> taskList = new ArrayList<>();
            File file = new File(filePath);
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String taskLine = s.nextLine();
                Task task = Parser.parseTask(taskLine);
                taskList.add(task);
            }
            System.out.println("Saved tasks has been successfully loaded.");
            return taskList;
        } catch (FileNotFoundException e) {
            throw new BobException("No saved tasks found.");
        }
    }
    public void saveTasks(TaskList taskList) throws BobException {
        try {
            ensureDataDirectoryExists();
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList.getTasks()) {
                fw.write(task.getTaskLine() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new BobException("Oh no! An error occurred while saving tasks: " + e.getMessage());
        }
    }
}
