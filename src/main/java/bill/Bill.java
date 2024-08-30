package bill;

/**
 * The Bill class provides a command line interface chatbot for users to interact with.
 */
public class Bill {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Initializes Bill chatbot.
     */
    public Bill() {
        storage = new Storage();
        ui = new Ui();
        tasks = new TaskList();
    }

    /**
     * Starts Bill chatbot when called.
     */
    public void start() {
        ui.silentLoadingData(storage, tasks.getUserList(), tasks);
        ui.introduce();
        ui.handleUserCommands(storage, tasks);
        ui.conclude();
    }

    public static void main(String[] args) {
        new Bill().start();
    }
}