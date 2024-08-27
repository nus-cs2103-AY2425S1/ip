import java.io.FileNotFoundException;

public class Joe {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Joe(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | CorruptedFileException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }


    public void run() {
        ui.runProgramme(tasks);
    }

    public static void main(String[] args) {
        new Joe("../src/main/data/joe.txt").run(); // remove ../ if running from the root directory
    }

}
