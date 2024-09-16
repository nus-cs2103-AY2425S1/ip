package thebotfather.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import thebotfather.task.Deadline;
import thebotfather.task.Event;
import thebotfather.task.Task;
import thebotfather.task.Todo;

/**
 * The Storage class handles the loading and saving of tasks from and to a file.
 * It provides methods to read tasks from a file and convert them into a TaskList object,
 * as well as to write the current TaskList back to the file.
 */
public class Storage {

    /**
     * The path of the file to store the task list.
     */
    private final String pathName;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param pathName The path of the file to store the task list.
     */
    public Storage(String pathName) {
        assert pathName != null && !pathName.trim().isEmpty() : "File path cannot be null or empty";
        this.pathName = pathName;
    }

    /**
     * Loads the task list from the specified file.
     * If the file does not exist, it creates a new file and returns an empty TaskList.
     *
     * @return The TaskList object containing tasks read from the file.
     * @throws TheBotFatherException If the file is corrupted or cannot be read properly.
     */
    public TaskList load() throws TheBotFatherException {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        try {
            File file = new File(this.pathName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            assert file.exists() : "Failed to create the new task file";
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] instructions = line.split(" \\| ");
                switch (instructions[0]) {
                case "T":
                    taskList.addTask(new Todo(instructions[2]));
                    break;
                case "D":
                    taskList.addTask(new Deadline(instructions[2],
                            LocalDateTime.parse(instructions[3])));
                    break;
                case "E":
                    taskList.addTask(new Event(instructions[2],
                            LocalDateTime.parse(instructions[3]),
                            LocalDateTime.parse(instructions[4])));
                    break;
                default:
                    throw new Exception("Error");
                }
                if (Objects.equals(instructions[1], "1")) {
                    taskList.markAsDone(taskList.numberOfElements() - 1);
                }
            }
        } catch (TheBotFatherException e) {
            throw e;
        } catch (FileNotFoundException e) {
            throw new TheBotFatherException("LOL file does not exist");
        } catch (Exception e) {
            throw new TheBotFatherException("The file is corrupted");
        }
        return taskList;
    }

    /**
     * Saves the current task list to the specified file.
     * If the file does not exist, it creates a new file.
     *
     * @param taskList The TaskList object containing tasks to be saved.
     * @throws TheBotFatherException If there is an error writing to the file.
     */
    public void toFile(TaskList taskList) throws TheBotFatherException {

        try {
            File file = new File(this.pathName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            assert file.exists() : "Failed to create the new task file";
            FileWriter fw = new FileWriter(file);
            fw.write(taskList.toFile());
            fw.close();
        } catch (IOException e) {
            throw new TheBotFatherException("Check the file path, I am sure you messed it up somehow");
        }
    }
}
