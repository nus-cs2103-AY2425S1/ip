package james;

import java.util.ArrayList;

/**
 * Manages the interaction between the user, task storage, and task operations.
 * <p>
 * Initializes the UI, storage, task list, and parser. Handles user commands
 * and controls the flow of the application.
 * </p>
 */
class James {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Creates a new James instance with the specified file path.
     *
     * @param filepath Path to the file where tasks are stored
     */
    public James(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        taskList = new TaskList(new ArrayList<>());
        parser = new Parser(ui, storage);

        storage.loadSavedData(taskList.getTasks());
    }

    /**
     * Starts the application, showing the greeting and processing user commands.
     * Continues until the user types "bye" to exit.
     */
//    public void run() {
//        ui.showGreeting();
//        boolean isExit = false;
//
//        while (!isExit) {
//            try {
//                String command = ui.readCommand();
//                isExit = parser.parseAndExecute(command, taskList);
//            } catch (JamesException e) {
//                ui.showMessage(e.getMessage());
//            }
//        }
//
//        ui.close();
//    }

    public String getResponse(String input) {
        String response;
        try {
            response = parser.parseAndExecute(input, taskList);
        } catch (JamesException e) {
            return e.getMessage();
        }
        return response;
    }
}
