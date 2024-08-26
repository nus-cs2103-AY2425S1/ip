package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

public class Storage {
    private final String TASK_FILE_PATH = "data/tasks.txt";
    private final String DIRECTORY_PATH = "data";

    public void loadTaskList(TaskList taskList) throws FileNotFoundException {
        File f = new File(TASK_FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String data = s.nextLine();
            taskList.addTask(fromData(data));
        }
    }

    private Task fromData(String data) {
        String[] args = data.split(" \\| ");
        return switch (args[0]) {
            case "T" -> new Todo(args[1], Boolean.parseBoolean(args[2]));
            case "D" -> new Deadline(args[1], Boolean.parseBoolean(args[2]), args[3]);
            case "E" -> new Event(args[1], Boolean.parseBoolean(args[2]), args[3], args[4]);
            default -> null;
        };
    }

    public void initFile() {
        try {
            File dir = new File(DIRECTORY_PATH);
            dir.mkdir();
            File f = new File(TASK_FILE_PATH);
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error initialising file, BotManager will now exit");
            System.exit(0);
        }
    }

    public void saveTaskList(TaskList taskList) throws IOException {
       FileWriter fw = new FileWriter(TASK_FILE_PATH);
       fw.write(taskList.toData());
       fw.close();
    }
}
