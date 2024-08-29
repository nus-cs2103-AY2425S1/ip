public class Krona {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Krona(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (KronaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            try {
                c.execute(tasks, ui, storage);
            } catch (KronaException e) {
                ui.showMessage("An error occurred: " + e.getMessage());
            }
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new Krona("data/tasks.txt").run();
    }

}