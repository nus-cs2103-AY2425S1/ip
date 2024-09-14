package bangmang.storage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import bangmang.exception.InvalidCommandException;
import bangmang.exception.InvalidTaskFormatException;
import bangmang.tasks.Task;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws InvalidCommandException {
        /**
         * Loads task data from harddrive and returns an array list of tasks
         * 
         * @return Array List of Tasks
         * @Throws InvalidCommandException if cannot read file
         * @Throws InvalidTaskFormatException if task cannot be created
         */

        ArrayList<Task> list = new ArrayList<>();
        File dataFile = new File(filePath);
        if (!dataFile.getParentFile().exists()) {
            dataFile.getParentFile().mkdirs();
        }
        if (dataFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        Task task = Task.readSavedTask(line);
                        list.add(task);
                    } catch (InvalidTaskFormatException e) {
                        System.out.println("Warning: Skipping invalid task format: " + line);
                    }
                }
            } catch (IOException e) {
                throw new InvalidCommandException("Error reading the file: " + e.getMessage());
            }
        }
        return list;
    }

    public void save(ArrayList<Task> list) throws InvalidCommandException {
        /**
         * Saves task data into harddrive
         * 
         * @Throws InvalidCommandException if cannot save file
         * @Throws InvalidTaskFormatException if task cannot be saved
         */

        File dataFile = new File(filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))) {
            for (Task task : list) {
                try {
                    bw.write(task.writeSavedTask());
                    bw.newLine();
                } catch (InvalidTaskFormatException e) {
                    System.out.println("Warning: Cannot write task: " + task.toString());
                }
            }
        } catch (IOException e) {
            throw new InvalidCommandException("Error writing to the file: " + e.getMessage());
        }
    }
}