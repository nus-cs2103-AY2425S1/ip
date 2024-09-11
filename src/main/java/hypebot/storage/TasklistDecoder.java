package hypebot.storage;

import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.Task;
import hypebot.task.TaskDateTimeParseException;
import hypebot.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static hypebot.common.Messages.ERROR_LOAD_TASKLIST;

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

    private Task loadTask(String[] taskTextLineElements) throws TaskDateTimeParseException {
        String taskType = taskTextLineElements[0];
        String taskName = taskTextLineElements[2];
        Task newTask = null;
        switch (taskType) {
        case "T":
            newTask = new ToDo(taskName);
            break;
        case "D":
            String dueDate = taskTextLineElements[3];
            newTask = new Deadline(taskName, dueDate);
            break;
        case "E":
            String startTime = taskTextLineElements[3];
            String endTime = taskTextLineElements[4];
            newTask = new Event(taskName, startTime, endTime);
            break;
        }
        return newTask;
    }

    /**
     * Decodes lines from .txt file into Tasks, adds them to an ArrayList to be returned.
     *
     * @return ArrayList containing Tasks decoded from .txt file.
     * @throws FileNotFoundException If file to decode and load Tasks from not found.
     */
    public ArrayList<Task> decode() throws FileNotFoundException {
        if (!tasklistFile.exists()) {
            throw new FileNotFoundException(ERROR_LOAD_TASKLIST);
        }
        Scanner scanner = new Scanner(tasklistFile);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String taskTextLine = scanner.nextLine();
            String[] taskTextLineElements = taskTextLine.split(" , ");
            Task newTask = loadTask(taskTextLineElements);
            if (newTask != null && taskTextLineElements[1].equals("1")) {
                newTask.mark();
            }
            tasks.add(newTask);
        }
        scanner.close();
        return tasks;
    }
}
