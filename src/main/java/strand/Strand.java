package strand;

import strand.Commands.Command;
import strand.Exceptions.*;

import java.util.Scanner;

public class Strand {
    private static final String FILENAME = "./data/strand.txt";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;


    public Strand(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (StrandException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        boolean isRunning = true;
        Scanner scan = new Scanner(System.in);
        while (isRunning) {
            String userInput = scan.nextLine();
            try {
                ui.showLine();
                Command c = Parser.parse(userInput);
                c.execute(tasks, ui, storage);
                isRunning = c.isRunning();
            } catch (StrandException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Strand(FILENAME).run();
    }
}
