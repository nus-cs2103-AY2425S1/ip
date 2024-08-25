import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Jag {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;



    public Jag(String path) {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
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
            } catch (AExceptions e) {
                ui.showError(e.getErrorMessage());
            }
        }
    }


    public static void main(String[] args) {
        new Jag("./data/jag.txt").run();
    }
}