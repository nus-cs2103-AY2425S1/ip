package ekud;

import ekud.commands.Command;
import ekud.components.Parser;
import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.components.Ui;
import ekud.exceptions.EkudException;

import java.util.Scanner;

public class Ekud {
    public static final String TASK_DATA_PATH = "data/tasks.txt";
    public static final String OUTPUT_PREFIX = "\t ";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Scanner sc;

    public Ekud(Scanner sc) {
        this.sc = sc;
        ui = new Ui(OUTPUT_PREFIX);
        storage = new Storage(TASK_DATA_PATH);
        tasks = storage.loadTasks(ui);
    }

    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(sc);
                ui.printLineSeparator();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (EkudException e) {
                ui.printOutput(e.getMessage());
            } finally {
                ui.printLineSeparator();
            }
        }
    }

}
