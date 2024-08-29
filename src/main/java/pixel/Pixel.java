package pixel;

import java.io.IOException;

import pixel.command.Command;
import pixel.task.TaskList;

public class Pixel {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Pixel(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.PixelSays(e.getMessage(), "Creating a new file!");
            taskList = new TaskList();
        }
    }

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
                ui.PixelSays(e.getMessage());
            } catch (IOException e) {
                ui.PixelSays(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Pixel("data.txt").run();
    }
}
