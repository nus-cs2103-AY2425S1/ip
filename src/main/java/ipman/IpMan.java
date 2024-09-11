package ipman;

import ipman.commands.Command;
import ipman.commands.Context;
import ipman.commands.ExitException;
import ipman.models.TaskList;
import ipman.models.TasksFileManager;
import ipman.parser.Parser;
import ipman.ui.Ui;

/**
 * Represents the main chat bot program that allows users to manage their tasks
 */
public class IpMan {
    private final Ui ui;
    private final TaskList tasks = new TaskList();
    private final TasksFileManager fileManager = new TasksFileManager("./data/ipman.txt");
    private final Context context;

    public IpMan(Ui ui) {
        this.ui = ui;
        this.context = new Context(tasks, ui, fileManager);
        fileManager.load(tasks);
    }

    /**
     * Executes a command interpreted from a message
     *
     * @param message message to parse and execute
     * @return whether the program should exit
     */
    public boolean executeMessage(String message) {
        try {
            Command command = Parser.parseCommand(message);
            command.execute(context);
            fileManager.save(tasks);
        } catch (ExitException e) {
            return true;
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }

        return false;
    }
}
