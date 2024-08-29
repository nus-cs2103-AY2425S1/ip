import darwin.command.Command;
import darwin.command.StartCommand;
import darwin.exception.DarwinException;
import darwin.parser.InputParser;

import darwin.task.TaskManager;
import darwin.ui.Ui;

public class Darwin {

    static final String NAME = "darwin";
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
