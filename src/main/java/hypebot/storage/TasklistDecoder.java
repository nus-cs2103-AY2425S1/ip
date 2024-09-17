package hypebot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import hypebot.exception.datetime.HypeBotDateTimeParseException;
import hypebot.parser.task.FileTaskParser;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;

import static hypebot.common.Messages.ERROR_LOCATING_TASKLIST;

/**
 * Represents a TasklistDecoder that takes in a File to load Tasks from
 * and adds Tasks to an ArrayList sent to create Tasklist objects.
 *
 * @author Youngseo Park (@youngseopark05)
 */
public class TasklistDecoder {
    private final File tasklistFile;
    private final FileTaskParser fileTaskParser;

    /**
     * Takes in a File containing lines of text that decode to Task objects
     * and creates a new TasklistDecoder.
     *
     * @param tasklistFile File object containing file path to .txt file with task data.
     */
    public TasklistDecoder(File tasklistFile) {
        this.tasklistFile = tasklistFile;
        this.fileTaskParser = new FileTaskParser();
    }

    private void addTaskFromText(String taskTextLine, Tasklist tasks)
            throws IllegalArgumentException, HypeBotDateTimeParseException {
        Task newTask = fileTaskParser.parse(taskTextLine);
        tasks.add(newTask);
    }

    /**
     * Decodes lines from .txt file into Tasks, adds them to a Tasklist,
     * then returns the Tasklist.
     *
     * @return Tasklist containing Tasks decoded from .txt file.
     * @throws FileNotFoundException If file to decode and load Tasks from not found.
     */
    public Tasklist decode() throws FileNotFoundException, HypeBotDateTimeParseException, IllegalArgumentException {
        if (!tasklistFile.exists()) {
            throw new FileNotFoundException(ERROR_LOCATING_TASKLIST + tasklistFile.getAbsolutePath());
        }

        Scanner scanner = new Scanner(tasklistFile);
        Tasklist tasks = new Tasklist(new ArrayList<>());

        while (scanner.hasNextLine()) {
            String taskText = scanner.nextLine();
            addTaskFromText(taskText, tasks);
        }

        scanner.close();

        return tasks;
    }
}
