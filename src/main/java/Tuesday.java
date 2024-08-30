public class Tuesday {
    private Storage storage;
    private Task tasks;
    private Ui ui;

    /**
     * Creates Tuesday.util.Ui, Tuesday.util.Storage & Tuesday.task.Task Object, and checks whether there exist a datafile
     */
    public Tuesday(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            // Add all the tasks in the data file to the Tasks class
            tasks = new Task(storage.load());
        } catch (TuesdayException e) {
            ui.showLoadingError();
            tasks = new Task(storage); // Create a new data file
        }
    }

    /**
     * Main function
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TuesdayException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Tuesday("src/main/data/tuesday.txt").run();
    }
}
