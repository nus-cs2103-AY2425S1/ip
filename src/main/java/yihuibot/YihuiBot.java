package yihuibot;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import yihuibot.exception.executable.ExecutableException;
import yihuibot.exception.parse.ParseException;
import yihuibot.exception.taskformat.IncorrectTaskFormatException;
import yihuibot.executable.Executable;
import yihuibot.executable.TaskModifier;
import yihuibot.storage.Storage;
import yihuibot.task.TaskList;
import yihuibot.ui.Ui;

/**
 * Responds to the user's dialog.
 *
 * @author Toh Yi Hui A0259080A
 */
public class YihuiBot {
    // The name of this bot
    private static final String NAME = "YihuiBot";

    // Format for date time of task
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    private Ui ui = new Ui(DATE_TIME_FORMAT);
    private Storage storage;
    private TaskList tasks;

    /**
     * Construct a new instance of YihuiBot.
     *
     * @param filePath the path to read task data from.
     * @throws InvalidPathException when filePath cannot be converted to a Path.
     * @throws IOException when an I/O error occurred when instantiating Storage.
     * @throws IncorrectTaskFormatExceptionn when data in filePath is corrupted.
     */
    public YihuiBot(String filePath) throws InvalidPathException, IOException,
            IncorrectTaskFormatException {
        storage = new Storage(filePath, DATE_TIME_FORMAT);
        tasks = storage.load();
    }

    public String greet() {
        return ui.prettyString("Hello! I am " + NAME + "!", "How may I help you?");
    }

    /**
     * Generates a response to the user based on what was sent.
     *
     * @param input the user's input.
     * @return a response.
     */
    public String getResponse(String input) throws IOException {
        try {
            // Returns an Executable based on what was parsed into the ui.
            Executable exec = ui.parse(input);

            if (exec instanceof TaskModifier) {
                TaskModifier taskModifier = (TaskModifier) exec;
                taskModifier.setTasks(tasks);
                exec = taskModifier;
            }

            // Checks if data needs to be save.
            if (exec.execute()) {
                storage.save(tasks);
            }

            return ui.prettyString(exec.getOutput());
        } catch (ParseException | ExecutableException e) {
            return ui.prettyString(e.getMessage());
        }
    }
}
