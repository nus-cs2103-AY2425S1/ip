package tomo;

import exception.StorageException;
import task.Converter;
import task.Event;
import task.Deadline;
import task.Task;
import task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles loading tasks from and storing tasks to from file 
*/

public class Storage {
    private File file;
    public Storage(String fileName) {
        this.file = new File(fileName);
    }

    /**
     * Loads the task list from file
     * 
     * @return The task list loaded from the file 
     * @throws StorageException If the storage file is not found
     */
    public TaskList load() throws StorageException {
        TaskList tasks = new TaskList();
        Scanner scanner;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new StorageException(e.getMessage());
        } 

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] args = line.split("\\[|\\]\\[|\\] ");
            if (args[1].equals("T")) {
                Task task = new ToDo(args[3]);
                if (args[2].equals("X")) {
                    task.mark();
                }
                tasks.add(task);
            } else if (args[1].equals("D")) {
                args = line.split("\\[|\\]\\[|\\] | \\(by: |\\)");
                Task task = new Deadline(args[3], Converter.OutputToDateTime(args[4]));
                if (args[2].equals("X")) {
                    task.mark();
                }
                tasks.add(task);
            } else {
                args = line.split("\\[|\\]\\[|\\] | \\(from: | to: |\\)");
                Task task = new Event(args[3], Converter.OutputToDateTime(args[4]), Converter.OutputToDateTime(args[5]));
                if (args[2].equals("X")) {
                    task.mark();
                }
                tasks.add(task);
            }
        }
        scanner.close();
        return tasks;
    }
    
    /**
     * Stores the task into file
     * 
     * @param tasks The task list to be stored 
     * @throws StorageException When encounters problem when trying writing to file
     */
    public void store(TaskList tasks) throws StorageException {
        FileWriter writer; 
        try {
            writer = new FileWriter(file);
            for (int i = 0; i < tasks.size(); ++i) {
                writer.write(tasks.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
    }
}