public class Alice {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Alice(String filePath) {
        storage = new Storage(filePath);
        // load tasks
        storage.loadTasks();
        tasks = storage.getTasks();

        // initialise UI with the loaded tasks
        ui = new Ui(tasks);
    }

    public static void main(String[] args) {
        // create instance of Alice with loaded tasks
        new Alice("data.txt").run();
    }

    // starts the program
    public void run() {
        ui.showWelcome();
        try {
            ui.getInput();
        } catch (AliceException e) {
            System.out.println(e);
            ui.showDivider();
        }

        ui.exitMessage();
        storage.saveTasks(tasks);
    }
}
