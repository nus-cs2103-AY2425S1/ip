package chatbaby;

import chatbaby.Command;
import chatbaby.Storage;
import chatbaby.Task;
import chatbaby.TaskType;
import chatbaby.TaskList;
import chatbaby.Ui;
import chatbaby.Parser;
import chatbaby.ChatBabyException;
import chatbaby.Deadline;
import chatbaby.Event;
import chatbaby.ToDo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws ChatBabyException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try (Scanner scanner = new Scanner(file)) {
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                try {
                    tasks.add(Task.fromFileText(line)); // Handle corrupted data inside the method
                } catch (Exception e) {
                    throw new ChatBabyException("Error while reading line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new ChatBabyException("Error while loading tasks: " + e.getMessage());
        }
        return tasks;
    }


    public void save(TaskList tasks) throws ChatBabyException {
        try {
            Path parentDir = Path.of(filePath).getParent();
            if (parentDir != null) {
                Files.createDirectories(parentDir);  // Creates the directory if it doesn't exist
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for (Task task: tasks.getTasks()) {
                bw.write(task.toFileText());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new ChatBabyException("Error while saving tasks: " + e.getMessage());
        }
    }
}
