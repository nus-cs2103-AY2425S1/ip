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
        assert ui != null : "UI can not be null";
        assert storage != null : "Storage can not be null";
        assert taskList != null : "TaskList can not be null";
    }

    /**
     * Run for command line interface
     */
    public void run() {
        ui.printWelcome();
        Scanner scanner = new Scanner(System.in);
        try {
            String Command = scanner.nextLine();
            assert command != null && !command.trim().isEmpty() : "Command cannot be null or empty";
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
            assert toReturn != null : "Response from parser should be a string";
        } catch (InputException e) {
            return e.getMessage();
        }
        return toReturn;
    }
}
