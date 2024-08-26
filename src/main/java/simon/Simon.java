package simon;

import java.util.Scanner;

/**
 * Represents the main application class for Simon.
 * This class handles the initialization of the user interface, storage, task list, and command parsing.
 * It also manages the application's main execution loop.
 */
public class Simon {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Parser parser;
    /**
     * Constructs a Simon application with the specified file path for storage.
     * Initializes the user interface, storage, and task list.
     * If loading the task list fails, an empty task list is created.
     *
     * @param filePath the path to the file where tasks are saved
     */
    public Simon(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Runs the main loop of the Simon application.
     * Continuously reads user input, parses commands, executes them, and updates the task list.
     * Exits when the user inputs "bye".
     */
    public void run() {
        //...
        ui.showWelcome();;
        Scanner sc = new Scanner(System.in);
        String input;
        storage.load();
        while (!(input = sc.nextLine().trim()).equals("bye")) {
            try {
                if (input.isEmpty()) {
                    continue;
                }
                else {
                    Command command = parser.parse(input);
                    assert command != null;
                    command.execute(tasks, ui, storage);
                }
            } catch (Error | Exception e) {
                System.out.print(e.toString());
            }
        }
        ui.showExit();
    }
    /**
     * The main entry point of the Simon application.
     * Initializes and starts the Simon application with the specified file path.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Simon("tasks.txt").run();
    }




}
