package Arona;

import java.nio.file.InvalidPathException;
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

                // Exit loop
                if (input.equalsIgnoreCase("bye")) {
                    // Exit programme
                    break;
                }
            } catch (Exception e) {
                // User Error
                if (e instanceof AronaException) {
                    ui.showException(e);
                }
                // Integer.parseInt() receives too long number
                else if (e instanceof NumberFormatException) {
                    ui.showNumberException();
                }
                // File not found or cant be read/write to
                else if (e instanceof java.io.IOException || e instanceof SecurityException) {
                    ui.showFileException();
                }
                // Path in arona::main not correct
                else if (e instanceof InvalidPathException) {
                    ui.showPathException();
                }
                // Other exception
                else {
                    //e.printStackTrace();
                    ui.showException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Arona(".\\data.txt").run();
    }
}