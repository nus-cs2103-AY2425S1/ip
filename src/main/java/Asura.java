import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Asura {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Asura(String filepath) {
        ui = new Ui();
        try {
            storage = new Storage(filepath);
            tasks = new TaskList(storage.load());
        } catch (AsuraException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean isExit = false;
        ui.showIntroduction();

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AsuraException e) {
                ui.showError(e.getMessage());
            }

        }

        ui.showGoodbye();
    }


    public static void main(String[] args) throws AsuraException {
        new Asura("data/asura.txt").run();
    }
}
