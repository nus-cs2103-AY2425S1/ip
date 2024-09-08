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
 * from the UI and prints an output to the UI.
 * <p/>
 * Each Ekud instance encapsulates a {@link Storage}, {@link TaskList}, and {@link Ui} component
 * which work together with the {@link Parser} to read and manipulate user input.
 * <p/>
 * EKuD starts by first greeting the user and then loading in previously saved tasks. After which,
 * EKuD continuously executes the {@link Command} returned from the {@link Parser} reading user inputs
 * until the user stops the program by giving the exit command using "bye" or "stop".
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

    /** Boolean flag which indicates if user has exited Ekud */
    private boolean hasExit = false;

    /**
     * Creates a new instance of the EKuD chat robot.
     *
     * @param ui The {@link Ui} which takes user input and prints output.
     */
    public Ekud(Ui ui) {
        assert ui != null : "Ui should not be null";

        this.ui = ui;
        storage = new Storage(TASK_DATA_PATH);
        tasks = new TaskList();
    }

    /**
     * Returns a boolean flag if the user has exited EKuD.
     *
     * @return {@link #hasExit}
     */
    public boolean isExited() {
        return hasExit;
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
     * Executes startup actions; greeting and load tasks.
     */
    public void runStartup() {
        // greet
        ui.printGreeting();

        // load storage
        loadTasks();
    }

    /**
     * Reads user input and executes commands.
     */
    public void runReadAndExecute() {
        try {
            String fullCommand = ui.readCommand();
            Command command = Parser.parse(fullCommand);
            command.execute(tasks, ui, storage);
            hasExit = command.isExit();
        } catch (EkudException e) {
            ui.addToBuffer(e.getMessage());
        } finally {
            if (!hasExit) {
                ui.printOutput();
            }
        }
    }

    /**
     * Executes actions after exiting Ekud; print goodbye.
     */
    public void runExit() {
        // say goodbye
        ui.printGoodbye();
    }

    /**
     * Runs EKuD in Text CLI.
     */
    public static void runTextMode() {
        // set up
        Scanner sc = new Scanner(System.in);
        TextUi ui = new TextUi(sc);
        Ekud ekud = new Ekud(ui);

        // ekud runs
        ekud.runStartup();
        while (!ekud.isExited()) {
            ekud.runReadAndExecute();
        }
        ekud.runExit();

        // close
        sc.close();
    }
}
