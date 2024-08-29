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
        tasks = new TaskList();
    }

    public void run() {
        // greet
        ui.printGreeting();

        // load storage
        ui.printOutput("Before we start, let me try to find your tasks!!");
        if (storage.doesPathExists()) {
            ui.printOutput("Found them! I'm going to load them into the system now!");
            storage.loadTasks(tasks, ui);
        } else {
            ui.printOutput("Hmm... It looks like your save doesn't exists!\nNo worries, I've make one for you!");
            storage.createPath(ui);
        }
        ui.printLineSeparator();

        // read commands
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


    /**
     * Runs the EKuD chat both.
     *
     * @param args Command line arguments - not used.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new Ekud(sc).run();
        sc.close();
    }

}
