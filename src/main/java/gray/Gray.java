package gray;

import gray.command.Command;

/**
 * The chatbot Gray.
 */
public class Gray {

    private static final String SAVE_TASKS_FILEPATH = "./data/saveTasks";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor to initialise Gray chatbot.
     * Takes in an argument to initialise the file save for tasks.
     * @param saveTasksFilepath
     */
    public Gray(String saveTasksFilepath) {
        ui = new Ui();
        storage = new Storage(saveTasksFilepath);
        try {
            tasks = storage.loadTasks();
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(ui, tasks);
                if (c.isExit()) {
                    break;
                }
            } catch (GrayException e) {
                ui.showError(e.getMessage());
            }
        }
        storage.saveTasks(tasks);
    }

    public static void main(String[] args) {
        new Gray(SAVE_TASKS_FILEPATH).run();
    }

}
