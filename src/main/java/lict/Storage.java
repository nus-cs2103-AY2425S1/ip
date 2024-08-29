package lict;

import lict.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String path) {
        this.file = new File(path);
    }

    public ArrayList<Task> load() throws LictException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String dataEntry = sc.nextLine();
                    Task task = Task.convertData(dataEntry);  // lict.task.Task class handles parsing
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                sc.close();
            } else {
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            }
            return tasks;
        } catch (IOException e) {
            throw new LictException("An error occurred while creating the data directory or the LictData.txt file: " + e.getMessage());
        }
    }

    public void save(TaskList taskList) throws LictException {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList.getTasks()) {
                writer.write(task.toData());
            }
            writer.close();
        } catch (IOException e) {
            throw new LictException("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}

