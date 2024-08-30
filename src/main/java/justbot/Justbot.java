package justbot;

import justbot.command.Command;
import justbot.exception.JustbotException;
import justbot.parser.Parser;
import justbot.storage.Storage;
import justbot.task.TaskList;
import justbot.ui.Ui;

public class Justbot {
    private Ui ui;
    private TaskList taskList;
    private Parser parser;
    private Storage storage;

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

    public static void main(String[] args) {
        new Justbot("/Users/justinyeo/Desktop/CS2103T-ip/data/justbottask.txt").run();
    }

}
