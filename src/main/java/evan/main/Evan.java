package evan.main;

import evan.command.Command;
import evan.exception.*;

public class Evan {
    private final Ui ui;
    private final UserInputParser userInputParser;
    private Storage storage;
    private TaskList taskList;

    public Evan(String filePath) {
        ui = new Ui();
        userInputParser = new UserInputParser();

        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (FileCreationException e) {
            ui.showLine();
            ui.showError(e.getMessage());
            System.exit(1);
        } catch (LoadingException e) {
            ui.showLine();
            ui.showError(e.getMessage());
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
                storage.save(taskList); // Save the task list after every update
                isExit = command.isExit();
            } catch (InvalidUserInputException e) {
                ui.showError(e.getMessage());
                ui.showValidCommands();
            } catch (NoSuchTaskException | SavingException e) {
                ui.showError(e.getMessage());
            }
        }
        System.exit(0);
    }
}
