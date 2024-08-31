package evan.main;

import evan.command.Command;
import evan.exception.*;

/**
 * The main class for the Evan chatbot.
 */
public class Evan {
    private final Ui ui;
    private final UserInputParser userInputParser;
    private Storage storage;
    private TaskList taskList;

    /**
     * Instantiates an Evan object.
     *
     * @param filePath File path of the .txt file that will store Evan's data.
     */
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

    /**
     * Starts the Evan chatbot.
     */
    public static void main(String[] args) {
        new Evan("data/tasks.txt").run();
    }

    /**
     * Starts the Evan chatbot.
     * The user will be prompted to enter a command in the command line after this method is called.
     */
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
