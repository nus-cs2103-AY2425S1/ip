import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
public class SeanBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public SeanBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Error loading tasks from file.");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
    
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = scanner.nextLine();
                parser.parse(userInput, tasks, ui, storage);
                if (userInput.equals("bye")) {
                    isExit = true;
                }
            } catch (SeanBotException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    
        scanner.close();
    }
    

    public static void main(String[] args) {
        new SeanBot("data/seanbot.txt").run();
    }
}
