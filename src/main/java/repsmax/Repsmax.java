package repsmax;

public class Repsmax {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Repsmax(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList();
        try {
            storage.load(tasks);
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isRunning = true;

        while (isRunning) {
            String userInput = ui.readCommand();
            if (userInput.equals("bye")) {
                isRunning = false;
            } else {
                parser.parse(userInput, tasks, ui, storage);
            }
        }

        ui.showGoodbye();
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new Repsmax("C:/Users/nicla/OneDrive/Desktop/Cs2103/repo/src/data.txt").run();
    }
}
