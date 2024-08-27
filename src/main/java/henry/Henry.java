package henry;

import henry.command.Command;

/**
 * a Personal Assistant Chatbot that helps a person
 * to keep track of various things.
 */
public class Henry {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Henry(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (HenryException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (HenryException e) {
                System.out.println("\nSorry! " + e.getMessage() + "\n");
            }
        }
        ui.bye();
    }

    public static void main(String[] args) {
        new Henry("./data/Henry.txt").run();
    }
}
