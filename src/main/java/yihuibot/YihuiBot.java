package yihuibot;

import java.util.NoSuchElementException;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.InvalidPathException;

import yihuibot.executable.Executable;
import yihuibot.executable.TaskModifier;

import yihuibot.exception.executable.ExecutableException;
import yihuibot.exception.parse.ParseException;
import yihuibot.exception.taskformat.IncorrectTaskFormatException;

import yihuibot.storage.Storage;

import yihuibot.task.TaskList;

import yihuibot.ui.Ui;

/**
 * Your friendly todolist bot.
 *
 * @author Toh Yi Hui A0259080A
 */
public class YihuiBot {
    // The name of this bot
    private static final String NAME = "YihuiBot";

    // Format for date time of task
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Construct a new instance of YihuiBot.
     *
     * @param filePath the path to read task data from.
     * @throws InvalidPathException when filePath cannot be converted to a Path.
     * @throws IOException when an I/O error occurred when instantiating Storage.
     */
    public YihuiBot(String filePath) throws InvalidPathException, IOException {
        ui = new Ui(DATE_TIME_FORMAT);
        storage = new Storage(filePath, DATE_TIME_FORMAT);
        try {
            tasks = storage.load();
        } catch (IncorrectTaskFormatException e) {
            String message = "Initializing an empty TaskList.";
            ui.warningPrint(e.getMessage(), message);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.greet(NAME);

        Scanner userInput = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = userInput.nextLine();

                // Returns an Executable based on what was parsed into the ui.
                Executable exec = ui.parse(input);

                if (exec instanceof TaskModifier) {
                    TaskModifier taskModifier = (TaskModifier) exec;
                    taskModifier.setTasks(tasks);
                    exec = taskModifier;
                }

                isExit = exec.execute();

                ui.prettyPrint(exec.getOutput());
            } catch (IllegalStateException | NoSuchElementException e) {
                ui.errorPrint("An error occurred!", e.getMessage());
                isExit = true;
            } catch (ParseException | ExecutableException e) {
                ui.prettyPrint(e.getMessage());
            }
        }

        userInput.close();
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.errorPrint("An error occured while writing to file.", e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            new YihuiBot("data/task.txt").run(); 
        } catch (InvalidPathException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
