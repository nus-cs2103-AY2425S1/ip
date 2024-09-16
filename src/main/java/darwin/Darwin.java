package darwin;

import darwin.command.Command;
import darwin.command.StartCommand;
import darwin.exception.DarwinException;
import darwin.gui.MainWindow;
import darwin.parser.InputParser;

import darwin.task.TaskManager;
import darwin.ui.Ui;

public class Darwin {
    static final String NAME = "darwin";
    private final TaskManager taskManager;
    private final Ui ui;
    private final InputParser parser;
    private String commandType;

    public Darwin(String path) {
        this.taskManager = new TaskManager(path);
        this.ui = new Ui();
        this.parser = new InputParser();
    }

    /**
     * Initialises a chat with the user and chat bot Darwin through standard input and output
     */
    public void run() {
        Command startCmd = new StartCommand(Darwin.NAME);
        try {
            startCmd.execute(this.taskManager, this.ui);
        } catch (DarwinException e) {
            this.ui.send("Failed to start.");
            return;
        }
        boolean isExit = false;
        while (!isExit) {
            try {
                Command cmd = parser.parse(this.ui.read());
                cmd.execute(this.taskManager, this.ui);
                isExit = cmd.isExit();
            } catch (DarwinException e) {
                this.ui.send(e.getMessage());
            }
        }
    }

    /**
     * Initialises a chat with the user and chat bot Darwin through GUI
     */
    public String getResponse(String input) {
        try {
            Command cmd = parser.parse(input);
            cmd.execute(this.taskManager, this.ui);
            // TODO: replace with CMD_WORD
            this.commandType = cmd.getClass().getSimpleName();
            return this.ui.getLastResponse();
        } catch (DarwinException e) {
            return e.getMessage();
        }
    }

    public String getCommandType() {
        return this.commandType;
    }

    public static void main(String[] args) {
        Darwin darwin = new Darwin("./tasks.txt");
        darwin.run();
    }
}
