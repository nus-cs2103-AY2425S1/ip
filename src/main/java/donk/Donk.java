package donk;

import donk.task.TaskList;
import java.util.Scanner;


public class Donk {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Donk class.
     *
     * @param filePath The path to the file where tasks are stored.
     *
     * Initializes the user interface (UI) and storage components. Attempts to load
     * tasks from the specified file. If loading fails, it displays a loading error
     * and initializes an empty task list.
     */
    public Donk(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DonkException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Donk application.
     *
     * Greets the user and continually reads input from the user. Processes each line
     * of input using the Parser class. If any exceptions are thrown during processing,
     * an error message is displayed to the user.
     */
    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String userInput = scanner.nextLine();
                Parser.parse(userInput, this.tasks, this.storage,this.ui);

            } catch (TodoException e) {
                System.out.println("    " + e.getMessage());
            } catch (Exception e) {
                System.out.println("    " + e.getMessage());

            }
        }
    }

    /**
     * The main method that starts the Donk application.
     *
     * @param args Command-line arguments (not used).
     *
     * Creates a new instance of Donk with the specified file path and runs the application.
     */
    public static void main(String[] args) {
        new Donk("./save.txt").run();
    }






}

