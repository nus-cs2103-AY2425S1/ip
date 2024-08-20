import exception.EchoBotException;
import task.Deadline;
import task.Event;
import task.ToDo;

import java.io.*;
import java.util.Scanner;
import java.nio.file.*;

public class FileManagement {
    private TaskList taskList;
    private final Path path;

    public FileManagement() {
        String home = System.getProperty("user.dir");

        Path directory = Paths.get(home, "src", "main", "data");
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.path = Paths.get(directory.toString(), "echobot.txt");
        if (!Files.exists(path)) {
            try {
                File file = new File(String.valueOf(path));
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {
        try {
            FileWriter fw = new FileWriter(String.valueOf(this.path));
            fw.write(this.taskList.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList load() {
        TaskList taskList = new TaskList();
        try {
            Scanner fr = new Scanner(new File(String.valueOf(this.path)));
            while (fr.hasNextLine()) {
                String taskString = fr.nextLine();
                String[] taskStringArray = taskString.split(" \\| ");
                if ("T".equals(taskStringArray[0])) {
                    taskList.addTask(new ToDo("1".equals(taskStringArray[1]), taskStringArray[2]));
                } else if ("D".equals(taskStringArray[0])) {
                    taskList.addTask(new Deadline("1".equals(taskStringArray[1]), taskStringArray[2], taskStringArray[3]));
                } else if ("E".equals(taskStringArray[0])) {
                    taskList.addTask(new Event("1".equals(taskStringArray[1]), taskStringArray[2], taskStringArray[3], taskStringArray[4]));
                }
            }
        } catch (FileNotFoundException | EchoBotException ignored) {
        }

        this.taskList = taskList;
        return taskList;
    }
}
