package spiderman;

public class Spiderman {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    public Spiderman(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromStorage());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDividerLine();
                Parser.parseInput(fullCommand, tasks);
                isExit = Parser.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDividerLine();
            }
        }
        storage.saveTasksToStorage(tasks.getTasks());
        ui.close();
    }

    public static void main(String[] args) {
        new Spiderman("tasks.txt").run();
    }

    public String getWelcome() {
        return "Hello! This is your friendly neighbourhood Spiderman.\n"
                + "What can I do for you?";
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String response = Parser.parseInput(input, tasks);
        boolean isBye = Parser.isExit();
        if (isBye) {
            storage.saveTasksToStorage(tasks.getTasks());
            this.isExit = true;
        }
        return response;
    }

    public boolean checkIsExit() {
        return this.isExit;
    }
}
