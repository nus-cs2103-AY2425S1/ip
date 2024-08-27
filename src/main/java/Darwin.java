import command.Command;
import command.StartCommand;
import exception.DarwinException;
import parser.InputParser;

import task.TaskManager;
import ui.Ui;

public class Darwin {

    static final String NAME = "Darwin";
    private final TaskManager taskManager = new TaskManager();

    private final Ui ui = new Ui();
    private final InputParser parser = new InputParser();

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

    public static void main(String[] args) {
        Darwin darwin = new Darwin();
        darwin.run();
    }
}
