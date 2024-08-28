
import java.io.FileNotFoundException;
import java.io.IOException;

public class NextGPT {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;


    public NextGPT(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load_tasks());
        } catch(IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean isExit = false;
        ui.openingMessage();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NextGPTException e) {
                ui.showError(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        new NextGPT("./data/tasks.txt").run();
    }
}
