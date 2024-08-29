package james;

import java.util.ArrayList;

class James {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public James(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        taskList = new TaskList(new ArrayList<>());
        parser = new Parser(ui, storage);

        storage.loadSavedData(taskList.getTasks());
    }

    public void run() {
        ui.showGreeting();
        boolean isExit = false;

        while (!isExit) {
            try {
                String command = ui.readCommand();
                isExit = parser.parseAndExecute(command, taskList);
            } catch (JamesException e) {
                ui.showMessage(e.getMessage());
            }
        }

        ui.close();
    }

    public static void main(String[] args) {
        new James("data/james.txt").run();
    }
}
