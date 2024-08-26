import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(tasks, ui);
    }

    public void run() {
        ui.showGreeting();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            parser.parse(input);
            try {
                storage.save(tasks.getTasks());
            } catch (IOException e) {
                ui.showSaveError(e.getMessage());
            }
            System.out.println("____________________________________________________________");
            input = scanner.nextLine();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}



