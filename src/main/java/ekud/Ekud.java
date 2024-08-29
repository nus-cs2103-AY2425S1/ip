package ekud;

import ekud.commands.Command;
import ekud.components.Parser;
import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.components.Ui;
import ekud.exceptions.EkudException;

import java.util.Scanner;

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

    /** Prefix prepended to responses generated from EKuD */
    public static final String OUTPUT_PREFIX = "\t ";

    /** The storage handler of EKuD */
    private final Storage storage;

    /** List of tasks being tracked */
    private final TaskList tasks;

    /** The ui handler of EKuD */
    private final Ui ui;

    /** Scanner instance where user input is read from */
    private final Scanner sc;

    /**
     * Creates a new instance of the EKuD chat robot.
     *
     * @param sc {@link Scanner} instance where input is read from user.
     */
    public Ekud(Scanner sc) {
        this.sc = sc;
        ui = new Ui(OUTPUT_PREFIX);
        storage = new Storage(TASK_DATA_PATH);
        tasks = new TaskList();
    }

    /**
     * Starts the EKuD chat robot program.
     * EKuD starts by first greeting the user and then loading in previously saved tasks. After which,
     * EKuD continuously executes the {@link Command} returned from the {@link Parser} reading user inputs
     * until the user stops the program by giving the exit command using "bye" or "stop".
     *
     * @see Parser#parse(String)
     * @see Command#execute(TaskList, Ui, Storage)
     */
    public void run() {
        // greet
        ui.printGreeting();

        // load storage
        ui.printOutput("Before we start, let me try to find your tasks!!");
        if (storage.hasExistingPath()) {
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
