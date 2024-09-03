package ekud;

import java.util.Scanner;

import ekud.commands.Command;
import ekud.components.Parser;
import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.exceptions.EkudException;
import ekud.ui.TextUi;
import ekud.ui.Ui;

/**
 * The Ekud class represents an instance of the EKuD chat robot that takes in user input
 * from the Command-Line-Interface (CLI) and prints an appropriate output.
 * <p/>
 * Each Ekud instance encapsulates a {@link Storage}, {@link TaskList}, and {@link Ui} component
 * which work together with the {@link Parser} to read and manipulate user input.
 *
 * @author uniqly
 */
public class Ekud {
    /** Relative path where the task data is stored */
    public static final String TASK_DATA_PATH = "data/tasks.txt";

    /** The storage handler of EKuD */
    private final Storage storage;

    /** List of tasks being tracked */
    private final TaskList tasks;

    /** The ui handler of EKuD */
    private final Ui ui;

    /**
     * Creates a new instance of the EKuD chat robot.
     *
     * @param ui The {@link Ui} which takes user input and prints output.
     */
    public Ekud(Ui ui) {
        this.ui = ui;
        storage = new Storage(TASK_DATA_PATH);
        tasks = new TaskList();
    }

    /**
     * Loads task data from storage.
     */
    public void loadTasks() {
        ui.addToBuffer("Before we start, let me try to find your tasks!!");
        if (storage.hasExistingPath()) {
            ui.addToBuffer("Found them! I'm going to load them into the system now!");
            storage.loadTasks(tasks, ui);
        } else {
            ui.addToBuffer("Hmm... It looks like your save doesn't exists!\nNo worries, I've make one for you!");
            storage.createPath(ui);
        }
        ui.printOutput();
    }

    /**
     * Starts the EKuD chat robot program in text CLI.
     * EKuD starts by first greeting the user and then loading in previously saved tasks. After which,
     * EKuD continuously executes the {@link Command} returned from the {@link Parser} reading user inputs
     * until the user stops the program by giving the exit command using "bye" or "stop".
     *
     * @see Parser#parse(String)
     * @see Command#execute(TaskList, Ui, Storage)
     */
    private void runTextMode() {
        // greet
        ui.printGreeting();

        // load storage
        loadTasks();

        // read commands and execute
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (EkudException e) {
                ui.addToBuffer(e.getMessage());
            } finally {
                ui.printOutput();
            }
        }

        // say goodbye
        ui.printGoodbye();
    }

    /**
     * Runs EKuD in Text CLI.
     */
    public static void textMode() {
        Scanner sc = new Scanner(System.in);
        TextUi ui = new TextUi(sc);
        new Ekud(ui).runTextMode();
        sc.close();
    }
}
