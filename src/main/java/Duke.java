import java.io.IOException;
import java.text.ParseException;
import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.ParseCommand(fullCommand);
                if(c != null){
                    c.execute(tasks, ui, storage);
                }
                isExit = Command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (ParseException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
