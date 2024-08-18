import common.Command;
import common.Parser;
import common.Ui;
import common.SkibidiException;
import storage.TaskStorage;

import java.util.Scanner;

public class Skibidi {
    private Ui ui;
    private Parser parser;
    private TaskStorage storage;

    public Skibidi() {
        ui = new Ui();
        parser = new Parser();
        storage = new TaskStorage();
    }

    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            try {
                String userInput = scanner.nextLine();
                Command command = parser.parse(userInput);
                isRunning = command.execute(ui, storage);
            } catch (SkibidiException e) {
                ui.printMessage(e.getMessage());
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Skibidi().run();
    }
}
