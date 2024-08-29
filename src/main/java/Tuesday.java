import java.sql.Array;
import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList; // import the ArrayList class
import java.lang.ArrayIndexOutOfBoundsException;

public class Tuesday {
    private Storage storage;
    private Task tasks;
    private Ui ui;

    public Tuesday(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            // Add all the tasks in the data file to the Tasks class
            tasks = new Task(storage.load());
        } catch (TuesdayException e) {
            ui.showLoadingError();
            tasks = new Task();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TuesdayException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Tuesday("src/main/data/tuesday.txt").run();
    }
}
