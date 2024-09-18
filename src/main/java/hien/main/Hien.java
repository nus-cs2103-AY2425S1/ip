package hien.main;

import java.time.format.DateTimeFormatter;

import hien.command.Command;
import hien.exception.HienException;
import hien.ui.UI;

public class Hien {
    private static final String DATA_FILE_PATH = "data/tasks.txt";
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");
    private TaskList tasks;
    private UI ui;
    private Storage storage;

    // Constructor
    public Hien() {
        ui = new UI();
        storage = new Storage(DATA_FILE_PATH);
        try {
            this.tasks = storage.load();
        } catch (HienException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Hien hien = new Hien();
        hien.runHien();
    }

    public void runHien() {
        ui.showWelcome();
        while (true) {
            String input = ui.readCommand();
            ui.showLine();
            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                if (c.isExit()) {
                    break;
                }
            } catch (HienException e) {
                ui.showError(e.getMessage());
            }

            ui.showLine();
        }

        ui.closeScanner();
    }

}



