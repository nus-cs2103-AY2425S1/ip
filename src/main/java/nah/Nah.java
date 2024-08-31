package nah;

import java.nio.file.Paths;

import nah.command.Command;
import nah.exceptions.NahException;
import nah.parser.Parser;
import nah.storage.Storage;
import nah.tasklist.TaskList;
import nah.ui.UI;

/**
 * Our chatBot
 */
public class Nah {
    private static final String hardDisk =
            Paths.get("D:", "cs2103T_week_2", "Data", "Nah.Nah.txt").toString();
    private Storage storage;
    private UI ui;
    private TaskList tasks;

    /**
     * Builds Nah chatBot based on a file of tasks.
     *
     * @param filePath the file of tasks
     */
    public Nah(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());

        } catch (NahException e) {
            ui.show(e.getMessage());
            storage = new Storage();
            tasks = new TaskList();
        }

    }

    /**
     * Runs the program
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NahException e) {
                ui.show(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {

        Nah nah = new Nah(hardDisk);
        nah.run();

    }
}
