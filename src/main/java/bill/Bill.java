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
     * Returns strings based on the route chosen by the user which is used in the Gui
     */
    public String getResponse(String userCommand) {
        return ui.handleUserCommandsGui(userCommand, storage, tasks);
    }

    public boolean checkIfRunning() {
        return ui.getIsRunning();
    }

    /**
     * Loads bill txt saved list data into Bill chatbot, in the Gui.
     */
    public void loadData() {
        ui.silenceLoadingData(storage, tasks.getUserList(), tasks);
    }

    /**
     * Starts Bill chatbot when called.
     */
    public void start() {
        ui.silenceLoadingData(storage, tasks.getUserList(), tasks);
        ui.introduce();
        ui.handleUserCommands(storage, tasks);
        ui.conclude();
    }

    public static void main(String[] args) {
        new Bill().start();
    }
}
