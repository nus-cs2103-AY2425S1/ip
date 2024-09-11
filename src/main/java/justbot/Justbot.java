package justbot;

import justbot.command.Command;
import justbot.exception.JustbotException;
import justbot.parser.Parser;
import justbot.storage.Storage;
import justbot.task.TaskList;
import justbot.ui.Ui;

/**
 * Represents Justbot, a task management chatbot.
 * It interacts with the user through the command line interface to manage tasks.
 */
public class Justbot {
    private Ui ui;
    private TaskList taskList;
    private Parser parser;
    private Storage storage;

    /**
     * Constructs a Justbot instance with the specified file path for task storage.
     *
     * @param filePath The file path where tasks are stored and loaded from.
     */
    public Justbot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();

        try {
            this.taskList = new TaskList(storage.loadTasks());
        } catch (JustbotException e) {
            ui.getJustBotExceptionMessage(e);
        }
    }

    /**
     * Runs the Justbot program. The program will continue accepting user commands
     * until the exit command is given.
     */
    public void run() {
        ui.botIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = parser.parseCommand(userInput);
                command.execute(this.taskList, this.ui, this.storage);
                isExit = command.isExit();
            } catch (JustbotException e) {
                ui.getJustBotExceptionMessage(e);
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            String response = command.execute(this.taskList, this.ui, this.storage);
            return response;
        } catch (JustbotException e) {
            return ui.getJustBotExceptionMessage(e);
        }
    }

    public Ui getUi() {
        return this.ui;
    }

}
