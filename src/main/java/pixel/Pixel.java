package pixel;

import java.io.IOException;

import pixel.command.Command;
import pixel.task.TaskList;

/**
 * The Pixel class represents the main class of the Pixel application. It
 * handles the initialization of the application and the execution of commands.
 */
public class Pixel {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Pixel object with the specified file path. It initializes the
     * user interface, storage, and task list. If the file specified by the file
     * path exists, it loads the tasks from the file. If the file does not exist, it
     * creates a new file.
     *
     * @param filePath The file path of the storage file.
     */
    public Pixel(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.pixelSays(e.getMessage(), "Creating a new file!");
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Pixel application. It displays the welcome message and prompts for
     * user commands. It executes the commands and saves the tasks to the storage
     * file.
     */
    public void run() {
        ui.printLine();
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parser(fullCommand);
                c.execute(taskList, ui, storage);
                storage.writeFile(taskList);
                isExit = c.isExit();
            } catch (PixelException e) {
                ui.pixelSays(e.getMessage());
            } catch (IOException e) {
                ui.pixelSays(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * The entry point of the Pixel application. It creates a new instance of Pixel
     * and runs the application.
     *
     * @param input The input command.
     * @return The response message from the Pixel application.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parser(input);
            String output = c.executeAndGetResponse(taskList, ui, storage);
            storage.writeFile(taskList);
            return output;
        } catch (PixelException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
