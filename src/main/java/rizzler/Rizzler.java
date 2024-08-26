package rizzler;

public class Rizzler {
    private Ui ui;
    private FileStorage fileStorage;
    private TaskList tasks;
    private Parser parser;

    public Rizzler(String filePath) {
        this.ui = new Ui();
        this.fileStorage = new FileStorage(filePath);
        this.tasks = new TaskList(this.fileStorage.load());
        this.parser = new Parser();
    }

    public void run() {
        this.ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = this.parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.fileStorage);
                isExit = c.isExit();
            } catch (RizzlerException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }
    public static void main(String[] args) {
        new Rizzler("data/saveData.txt").run();
    }
}
