import java.util.Scanner;


public class Lutchat {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Lutchat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Error Loading Tasks... " + e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            String userInput = scanner.nextLine();
            isRunning = Parser.parse(userInput, ui, tasks, storage);
        }

        storage.save(tasks);
        ui.exit();
        scanner.close();
    }

    public static void main(String[] args) {
        String filePath = "data/tasks.txt";
        Lutchat lutchat = new Lutchat(filePath);
        lutchat.run();
    }
}
