package chatbaby;

import java.io.File;
import java.util.Locale;

public class ChatBaby {
    private static final String FILE_PATH = "." + File.separator +
            "data" + File.separator + "chatBaby.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public ChatBaby() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatBabyException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String curCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(curCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatBabyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
        try {
            storage.save(tasks);
        } catch (ChatBabyException e) {
            ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        new ChatBaby().run();
    }
}
