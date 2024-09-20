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
    public Chobo() throws InputException {
        ui = new Ui();
        storage = new Storage(FILE_PATH,ui);
        taskList = storage.loadTasks();
        assert ui != null : "UI can not be null";
        assert storage != null : "Storage can not be null";
        assert taskList != null : "TaskList can not be null";
    }

    public String getResponse(String input) {
        Scanner scanner = new Scanner(input);
        String toReturn;
        try {
            String fullCommand = scanner.nextLine();
            Command command = Parser.parse(fullCommand, taskList, ui, storage);
            toReturn = command.execute(taskList, ui, storage);
            assert toReturn != null : "Response from parser should be a command";
        } catch (InputException e) {
            return e.getMessage();
        } finally {
            scanner.close();
        }
        return toReturn;
    }
}
