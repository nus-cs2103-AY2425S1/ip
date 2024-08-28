package evan.main;

import evan.command.Command;
import evan.exception.EvanException;
import evan.exception.InvalidUserInputException;
import evan.exception.NoSuchTaskException;

public class Evan {
    private final Ui ui;
    private final Storage storage;
    private final UserInputParser userInputParser;
    private TaskList taskList;


    public Evan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        userInputParser = new UserInputParser();

        try {
            taskList = new TaskList(storage.load());
        } catch (EvanException e) {
            ui.showError("An error occurred while loading the tasks from '" + filePath + "'.");
            taskList = new TaskList();
        }

    }

    public static void main(String[] args) {
        new Evan("data/tasks.txt").run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.getUserInput();
                Command command = userInputParser.parse(userInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (InvalidUserInputException e) {
                ui.showError(e.getMessage());
                ui.showValidCommands();
            } catch (NoSuchTaskException e) {
                ui.showError(e.getMessage());
            }
        }
        System.exit(0);
    }
}
