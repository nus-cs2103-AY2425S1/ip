import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

public class Dudu {
//    enum Command {
//        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, HELP
//    }

    private TaskList tasks;
    private UI ui;
    private Storage storage;

    public Dudu(String filePath) {
        ui =  new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (Exception e) {
//            tasks = new TaskList();
//        }

    }

    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Dudu("./data/dudu.txt").run();
    }
}
