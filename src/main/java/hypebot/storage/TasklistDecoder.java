package hypebot.storage;

import static hypebot.common.Messages.ERROR_LOAD_TASKLIST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import hypebot.parser.TaskParser;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;

/**
 * Represents a TasklistDecoder that takes in a File to load Tasks from
 * and adds Tasks to an ArrayList sent to create Tasklist objects.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class TasklistDecoder {
    private File tasklistFile;

    /**
     * Takes in a File containing lines of text that decode to Task objects
     * and creates a new TasklistDecoder.
     *
     * @param tasklistFile File object containing file path to .txt file with task data.
     */
    public TasklistDecoder(File tasklistFile) {
        this.tasklistFile = tasklistFile;
    }

    /**
     * Decodes lines from .txt file into Tasks, adds them to a Tasklist,
     * then returns the Tasklist.
     *
     * @return Tasklist containing Tasks decoded from .txt file.
     * @throws FileNotFoundException If file to decode and load Tasks from not found.
     */
    public Tasklist decode() throws FileNotFoundException {
        Scanner scanner = new Scanner(tasklistFile);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String taskTextLine = scanner.nextLine();
            try {
                Task newTask = TaskParser.parseTaskFromFile(taskTextLine);
                tasks.add(newTask);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
        return new Tasklist(tasks);
    }
}
