package repsmax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList tasks) {
        try {
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : tasks.getTasks()) {
                fileWriter.write(task.toFileFormat() + "\n");
            }
            fileWriter.close();
            System.out.println("Tasks saved to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }

    public void load(TaskList tasks) {
        try{
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("No task found. Starting a new List");
                return;
            }
            Scanner fileScanner = new Scanner(file);
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                tasks.add(Task.fromFileFormat(line));
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks.");
        }
    }
}