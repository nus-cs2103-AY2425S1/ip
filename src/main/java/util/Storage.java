package util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Task;
import task.TaskList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            return tasks;
        }
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNext()) {
            String taskLine = fileReader.nextLine();
            Task task = Parser.parseTask(taskLine);
            tasks.add(task);
        }
        fileReader.close();
        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task task : tasks.getTasks()) {
            fileWriter.write(task.toFileString() + "\n");
        }
        fileWriter.close();
    }
}
