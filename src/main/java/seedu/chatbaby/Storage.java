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
import java.io.*;
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

    //    private static ArrayList<Task> loadTasks() {
//        ArrayList<Task> tasks = new ArrayList<>();
//        File file = new File(FILE_PATH);
//        if (!file.exists()) {
//            return tasks; // Return an empty list if the file doesn't exist
//        }
//
//        try (Scanner scanner = new Scanner(file)) {
//            String line;
//            while (scanner.hasNextLine()) {
//                line = scanner.nextLine();
//                try {
//                    tasks.add(Task.fromFileText(line)); // Handle corrupted data inside the method
//                } catch (Exception e) {
//                    System.out.println("Error while reading line: " + line);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Error while loading tasks: " + e.getMessage());
//        }
//
//        return tasks;
//    }

    public void save(TaskList tasks) throws ChatBabyException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task: tasks.getTasks()) {
                bw.write(task.toFileText());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new ChatBabyException("Error while saving tasks: " + e.getMessage());
        }
    }
}
