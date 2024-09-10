package echobot.io;

import echobot.exception.EchoBotException;
import echobot.task.Deadline;
import echobot.task.Event;
import echobot.task.TaskList;
import echobot.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileManagement {
    private TaskList taskList;
    private final Path path;

    public FileManagement() {
        String home = System.getProperty("user.dir");
        final String FILE_NAME = "echobot.txt";

        Path directory = Paths.get(home, "src", "main", "data");
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.path = Paths.get(directory.toString(), FILE_NAME);
        if (!Files.exists(path)) {
            try {
                File file = new File(String.valueOf(path));
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Saves the task list as a file.
     */
    public void save() {
        try {
            FileWriter fw = new FileWriter(String.valueOf(this.path));
            final String listToBeWritten = this.taskList.save();
            fw.write(listToBeWritten);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the task list from the file.
     *
     * @return The task list loaded from the file.
     */
    public TaskList load() {
        TaskList taskList = new TaskList();
        final String SPLIT_KEYWORD = " \\| ";
        try {
            Scanner fr = new Scanner(new File(String.valueOf(this.path)));
            while (fr.hasNextLine()) {
                String taskString = fr.nextLine();
                String[] taskStringArray = taskString.split(SPLIT_KEYWORD);
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
