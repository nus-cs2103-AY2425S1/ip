package hypebot.storage;

import static hypebot.common.Messages.ERROR_LOCATING_TASKLIST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import hypebot.exception.datetime.HypeBotDateTimeParseException;
import hypebot.exception.illegal.DatePassedException;
import hypebot.exception.illegal.IllegalTaskStatusException;
import hypebot.exception.illegal.IllegalTaskTypeException;
import hypebot.parser.task.FileTaskParser;
import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;

/**
 * Represents a {@code TasklistDecoder} that decodes {@link Task}s in a {@link File}
 * using a {@link FileTaskParser}, and adds parsed {@link Task}s to a {@link Tasklist}.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see FileTaskParser
 * @see TasklistEncoder
 */
public class TasklistDecoder {
    /** {@link File} containing data that decodes to a {@link Task}. */
    private final File tasklistFile;

    /** {@link FileTaskParser} that parses data in {@code tasklistFile} into {@link Task}s. */
    private final FileTaskParser fileTaskParser;

    /**
     * Takes in a {@link File} containing lines of text that decode to
     * {@link Task} objects and creates a new {@code TasklistDecoder}.
     *
     * @param tasklistFile {@link File} containing saved {@link Task} data.
     */
    public TasklistDecoder(File tasklistFile) {
        this.tasklistFile = tasklistFile;
        this.fileTaskParser = new FileTaskParser();
    }

    /**
     * Takes in a {@link String} line from the {@link File} {@code tasklistFile},
     * has {@link FileTaskParser} {@code fileTaskParser} parse the line into a new
     * {@link Task}, and adds it to the {@link Tasklist} taken in.
     *
     * @param taskTextLine {@link String} line from the {@link File} {@code tasklistFile}.
     * @param tasks        {@link Tasklist} where {@link Task}s are being loaded into.
     * @throws DatePassedException           If a {@link Deadline}'s due date has passed current
     *                                       date, or an {@link Event} has already concluded.
     * @throws HypeBotDateTimeParseException If {@link Deadline}'s due date or {@link Event} times
     *                                       encoded in an incorrect format.
     * @throws IllegalTaskStatusException    If a {@link Task}'s completion status is not an
     *                                       accepted value.
     * @throws IllegalTaskTypeException      If no accepted task type is detected.
     */
    private void addTaskFromText(String taskTextLine, Tasklist tasks) throws DatePassedException,
            HypeBotDateTimeParseException, IllegalTaskStatusException, IllegalTaskTypeException {
        Task newTask = fileTaskParser.parse(taskTextLine);
        tasks.add(newTask);
    }

    /**
     * Scans each line from {@link File} {@code tasklistFile} using a {@link Scanner}
     * for {@link FileTaskParser} {@code fileTaskParser} to parse into {@link Task}s,
     * adds them to a new {@link Tasklist}, then returns the new {@link Tasklist}.
     *
     * @return {@link Tasklist} with {@link Task}s decoded from {@link File} {@code tasklistFile}.
     * @throws FileNotFoundException         If {@link File} {@code tasklistFile} does not exist.
     * @throws HypeBotDateTimeParseException If {@link Deadline}'s due date or {@link Event} times
     *                                       encoded in an incorrect format.
     * @throws IllegalArgumentException      If either a {@link Deadline}'s due date has passed,
     *                                       or an {@link Event} has already concluded; if a
     *                                       {@link Task}'s completion status is not accepted;
     *                                       or if an unaccepted task type is found.
     */
    public Tasklist decode() throws FileNotFoundException, HypeBotDateTimeParseException, IllegalArgumentException {
        if (!tasklistFile.exists()) {
            throw new FileNotFoundException(ERROR_LOCATING_TASKLIST + tasklistFile.getAbsolutePath());
        }

        Scanner scanner = new Scanner(tasklistFile);
        Tasklist tasks = new Tasklist();

        while (scanner.hasNextLine()) {
            String taskText = scanner.nextLine();
            addTaskFromText(taskText, tasks);
        }

        scanner.close();

        return tasks;
    }
}
