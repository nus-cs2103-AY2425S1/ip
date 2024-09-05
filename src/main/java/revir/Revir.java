package revir;

import java.io.IOException;
import java.nio.file.Path;

import revir.system.Storage;
import revir.tasks.TaskList;
import revir.user.Parser;
import revir.user.Ui;
import revir.user.command.Command;

/**
 * The Revir class is the main class that runs the Revir program.
 * It initializes the necessary components and runs the main loop to read and execute commands.
 */
public class Revir {
    private static TaskList taskList;
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;

    public static void main(String[] args) {
        parser = new Parser();
        ui = new Ui("Revir");
        storage = new Storage(Path.of("data", "tasks.dat"));
        taskList = new TaskList(storage, ui);
        ui.showWelcome();
        boolean exit = false;
        while (!exit) {
            String input = ui.readInput();
            try {
                Command c = parser.parse(input);
                c.execute(ui, taskList);
                exit = c.isExit();
            } catch (NumberFormatException e) {
                ui.showError("Invalid task index. Expected a number.");
            } catch (IOException e) {
                ui.showError("Unable to save file: " + e.getMessage());
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showExit();
    }

}
