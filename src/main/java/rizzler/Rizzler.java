package rizzler;

/**
 * Main class that controls Rizzler's functions.
 */
public class Rizzler {
    private Ui ui;
    private FileStorage fileStorage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructs a new <code>Rizzler</code> with the
     * given file path of the save file to load from.
     *
     * @param filePath File path to load save from.
     */
    public Rizzler(String filePath) {
        this.ui = new Ui();
        this.fileStorage = new FileStorage(filePath);
        this.tasks = new TaskList(this.fileStorage.load());
        this.parser = new Parser();
    }

    /**
     * Initiates the running of Rizzler.
     */
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

    /**
     * Starts the run process of Rizzler with data/saveData.txt
     * as the file to load from and save to.
     *
     * @param args Command line arguments which are not needed for Rizzler.
     */
    public static void main(String[] args) {
        new Rizzler("data/saveData.txt").run();
    }
}
