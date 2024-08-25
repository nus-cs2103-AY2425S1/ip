public class Ollie {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs an Ollie instance with a new TaskList.
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
     * The main method that runs the Ollie task manager.
     *
     * @param args Command-line arguments (not used).
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