import java.util.Scanner;

public class Nugget {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Nugget(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser(tasks);
    }

    public void run() {
        ui.printIntro();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine().trim();
            try {
                if (command.equals("bye")) {
                    break;
                }
                Command c = parser.parse(command);
                c.execute(tasks, ui, storage);
            } catch (NuggetException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.printEnd();
    }

    public static void main(String[] args) {
        new Nugget("data/nugget.txt").run();
    }
}
