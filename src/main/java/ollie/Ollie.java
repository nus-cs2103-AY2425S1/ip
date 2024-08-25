package ollie;

import ollie.exception.OllieException;

public class Ollie {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs an ollie.Ollie instance with a new ollie.TaskList.
     */
    public Ollie() {
        ui = new Ui();
        try {
            this.storage = new Storage("./data/ollie.txt");
            this.taskList = new TaskList(storage.loadTasks(), storage);
            ui.setTaskList(taskList);
        } catch (OllieException e) {
            ui.showMessage(e.getMessage());
        }
    }

    /**
     * The main method that runs the ollie.Ollie task manager.
     *
     * @param args ollie.Command-line arguments (not used).
     */
    public static void main(String[] args) throws OllieException {
        Ollie ollie = new Ollie();
        ollie.ui.greeting();
        System.out.println();

        String command;

        do {
            command = ollie.ui.readCommand();
            ollie.ui.respond(command);
        } while (!command.equals("bye"));

        ollie.ui.close();
    }
}