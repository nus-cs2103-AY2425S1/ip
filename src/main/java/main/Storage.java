package main;

import exception.CommandFoundButInvalidException;
import exception.InvalidSyntaxException;
import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;
    private File file;
    public Storage(String filePath) {
        // create the file if it does not exist
        filePath = filePath;
        file = new File(filePath);
        file.getParentFile().mkdirs();

        try {
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
    // retrieves the previously loaded arraylist
    /**
     * T | 1 | read book
     * D | 0 | return book | June 6th
     * E | 0 | project meeting | Aug 6th 2-4pm
     * T | 1 | join sports club
     */
    public List<Task> load() throws CommandFoundButInvalidException {
        List<Task> allTasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            if (!file.exists()) {
                return allTasks;
            }
            // iterate through the buffered reader
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\| ");
                Task currTask;
                switch (parts[0].trim()) {
                    case "T":
                        currTask = new ToDos(parts[2]);
                        break;
                    case "D":
                        currTask = new Deadlines(parts[2]);
                        break;
                    case "E":
                        currTask = new Events(parts[2]);
                        break;
                    default:
                        throw new InvalidSyntaxException("File is corrupted");
                }
                if (Integer.parseInt(parts[1].trim()) == 1) {
                    currTask.markAsDone();
                }
                allTasks.add(currTask);
            }

        } catch (IOException e) {
            System.out.println("Error when reading file. " + e.getMessage());
        }
        return allTasks;
    }
    // writes a new task into the file
    public void put(List<Task> allTasks) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
            for (Task curr : allTasks) {
                bw.append(curr.getInitDesc());
                bw.append("\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred when saving");
        }
    }
}
