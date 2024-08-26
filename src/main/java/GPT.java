public class GPT {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public GPT(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTasks());
    }

    public void run() {
        ui.showWelcomeMessage("GPT");

        while (true) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parseCommand(input);
                command.execute(taskList, ui, storage);

                if (command.isExit()) {
                    break;
                }
            } catch (GPTException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }
        }

        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new GPT("data/tasks.txt").run();
    }
}
