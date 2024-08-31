package orion.chatbot;

import orion.commands.Command;
import orion.exceptions.OrionException;

public class Orion {

    private boolean isOnline;

    private Ui ui;

    private Storage storage;

    private TaskList tasks;

    public Orion() {
        isOnline = true;
        ui = new Ui();
        storage = new Storage();

        ui.printGreet();

        try {
            tasks = new TaskList(storage.loadTasks());
            ui.printLoadTasks(tasks.getNoTasks());
        } catch (OrionException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        } finally {
            ui.printBar();
        }
    }

    public void run() {
        while (isOnline) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(tasks, storage, ui);
                isOnline = !c.isExit();
            } catch (OrionException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (Exception e) {
                ui.printErrorMessage("Unexpected error!" + e.getMessage());
            } finally {
                ui.printBar();
            }
        }

        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Orion().run();
    }
}