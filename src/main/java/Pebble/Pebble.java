package pebble;

import java.io.IOException;
import java.util.Scanner;

public class Pebble {
    private Ui ui;
    private Storage storage;
    private TasksList tasksList;

    public Pebble(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasksList = new TasksList(storage.loadTasks());
        } catch (IOException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasksList = new TasksList();
        }
    }

    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            Command command = Parser.parseCommand(input);
            command.execute(tasksList, ui, storage);
            if (command.getCommandType() == CommandType.BYE) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        // launch with data file
        new Pebble("data/pebble.txt").run();
    }
}
