package revir;

import java.nio.file.Path;

import revir.system.Storage;
import revir.tasks.TaskList;
import revir.user.ui.Ui;
import revir.user.ui.gui.Gui;
import revir.user.ui.tui.Tui;

/**
 * The Revir class is the main class that runs the Revir program.
 * It initializes the necessary components and runs the main loop to read and
 * execute commands.
 */
public class Revir {
    private static TaskList taskList;
    private static Storage storage;
    private static Ui ui;

    public static void main(String[] args) {
        storage = new Storage(Path.of("data", "tasks.dat"));
        taskList = new TaskList(storage, ui);
        if (args.length >= 1 && args[0].equals("--cli")) {
            ui = new Tui();
        } else {
            ui = new Gui();
        }

        ui.run(taskList);
        ui.showExit();
    }

}
