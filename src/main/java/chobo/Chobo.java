package chobo;

import java.util.Scanner;

/**
 * The type Chobo.
 */
public class Chobo {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private static final String FILE_PATH = "./data/chobo.txt";

    /**
     * Instantiates a new Chobo.
     */
    public Chobo() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        taskList = new TaskList(storage.loadTasks());
    }


    /**
     * Run.
     */
    public void run() {
        ui.printWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String Command = scanner.nextLine();
                isExit = Parser.parse(Command, taskList, ui, storage);
            } catch (InputException e) {
                ui.printError(e.getMessage());
            }
        }
        ui.printGoodbye();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Chobo().run();
    }
}






