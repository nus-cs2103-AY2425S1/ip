import java.io.IOException;

public class Sage {
    public static TaskList taskList;
    public static Ui ui;
    public static Storage storage;

    public static void main(String[] args) {
        new Sage("data/sage.txt").run();
    }

    public Sage(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
        } catch (IOException | SageException e) {
            ui.showError(e.getMessage());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command command = Parser.parse(input);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();

            } catch (SageException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
