package Bob;

import Bob.Storage.Storage;
import Bob.TaskList.TaskList;
import Bob.Ui.Ui;
import Bob.Parser.Parser;
import Bob.Command.Command;
import Bob.Exception.BobException;

import java.util.Scanner;

public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (BobException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine().trim();
            try {
                Command command = parser.parse(input);
                command.execute(tasks.getTasks(), storage, ui);
            } catch (BobException e) {
                ui.showError(e.getMessage());
            }
        } while (!input.equals("bye"));

        ui.showGoodbye();
        scanner.close();
    }

    public static void main(String[] args) {
        new Bob("./data/bob.csv").run();
    }
}
