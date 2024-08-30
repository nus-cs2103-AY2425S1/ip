public class Atreides {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Atreides(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AtreidesException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                if (isExit) {
                    storage.writeTasks(tasks);
                    ui.showExit();
                }
            } catch (AtreidesException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Atreides("src/main/Atreides.txt").run();
    }
}