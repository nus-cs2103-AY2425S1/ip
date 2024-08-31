package Arona;

import java.util.Scanner;
import java.util.ArrayList;


public class Arona {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Arona(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showException(e);
            tasks = new TaskList(new ArrayList<String>());
        }
    }

    public void run() {
        ui.showGreeting();

        // For user input
        Scanner in = new Scanner(System.in);

        // Process inputs
        while (true) {
            try {
                String input = in.nextLine();
                Parser.parse(input, storage, tasks, ui);
                if (input.equalsIgnoreCase("bye")) {
                    break;
                }
            } catch (Exception e) {
                ui.showException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Arona(".\\data.txt").run();
    }
}