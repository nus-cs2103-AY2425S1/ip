package skibidi;

import storage.TaskStorage;

import java.io.IOException;
import java.util.Scanner;

public class Skibidi {
    private final Ui ui;
    private final Parser parser;
    private TaskStorage storage;

    public Skibidi() {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new TaskStorage("data/tasks.txt");
        } catch (IOException e) {
            ui.printMessage("Error loading tasks: " + e.getMessage());
            storage = null;
        }
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
