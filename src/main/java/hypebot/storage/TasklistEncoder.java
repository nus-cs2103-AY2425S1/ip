package hypebot.storage;

import static hypebot.common.Messages.ERROR_SAVE_TASKLIST;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import hypebot.main.HypeBot;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;

/**
 * Represents a {@code TasklistEncoder} that encodes {@link Task}s in a {@link HypeBot}-
 * contained {@link Tasklist} to a given {@link File}.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see TasklistDecoder
 */
public class TasklistEncoder {
    /** {@link File} to save all {@link Task}s in a {@link Tasklist}. */
    private final File tasklistFile;

    /** {@link Tasklist} to save to given {@link File} {@code tasklistFile}. */
    private final Tasklist tasks;

    /**
     * Takes in a {@link Tasklist} to save {@link Task}s from and a {@link File} to
     * {@code encode()} {@link Task}s to and creates a new {@code TasklistEncoder}.
     *
     * @param tasklistFile {@link File} to save {@link Tasklist} data to.
     * @param tasks        {@link Tasklist} containing {@link Task}s to be saved.
     */
    public TasklistEncoder(File tasklistFile, Tasklist tasks) {
        this.tasklistFile = tasklistFile;
        this.tasks = tasks;
    }


    /**
     * Encodes {@link Task}s in the {@code TasklistEncoder}'s {@code tasks} in a
     * {@link TasklistDecoder}-decodeable format using a {@link FileWriter}.
     *
     * @throws IOException If {@link File} to save to does not exist or an unexpected
     *                     error occurs when encoding with the {@link FileWriter}.
     */
    public void encode() throws IOException {
        if (!tasklistFile.exists()) {
            throw new FileNotFoundException(ERROR_SAVE_TASKLIST);
        }
        FileWriter tasklistWriter = new FileWriter(tasklistFile);
        tasklistWriter.write("");
        for (Task task : tasks) {
            tasklistWriter.append(task.toFileString());
        }
        tasklistWriter.close();
    }
}
