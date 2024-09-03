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
        try {
            String Command = scanner.nextLine();
            Parser.parse(Command, taskList, ui, storage);
        } catch (InputException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Chobo().run();
    }

    public String getResponse(String input) {
        Scanner scanner = new Scanner(input);
        String toReturn;
        try {
            String Command = scanner.nextLine();
            toReturn = Parser.parse(Command, taskList, ui, storage);
        } catch (InputException e) {
            return e.getMessage();
        }
        return toReturn;
    }
}






