package bob;

public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Bob(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = this.storage.loadFile();
        } catch (IllegalInputException e) {
//            ui.showLoadingError();
            this.tasks = new TaskList();
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
            } catch (IllegalInputException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Bob("savedTasks.txt").run();
    }
}
