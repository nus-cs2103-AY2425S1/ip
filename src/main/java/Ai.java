import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ai {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private String filePath;

    public Ai(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AiException | IOException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showGreetings();
        boolean isExit = false;

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String reply;

        while (!isExit) {
            try {
                reply = scanner.nextLine();
                ui.showLine();
                Command c = Parser.parse(reply);
                c.execute(tasks, ui);
                isExit = c.isExit();
                tasks.save(filePath);
            } catch (AiException e) {
                ui.showError(e.getMessage());
                ui.showLine();
            } finally {
                ui.showLine();
                tasks.save(filePath);
            }
        }
    }
    public static void main(String[] args) throws AiException {
        new Ai("./data/Ai.txt").run();
    }
}