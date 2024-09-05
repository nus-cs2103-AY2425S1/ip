package ipman;

import ipman.commands.Command;
import ipman.commands.Context;
import ipman.commands.ExitException;
import ipman.models.TaskList;
import ipman.models.TasksFileManager;
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
    }

    public void execute() {
        ui.showWelcome();
        fileManager.load(tasks);

        // Main loop
        while (true) {
            try {
                Command command = ui.readMessage();
                command.execute(context);
                fileManager.save(tasks);
            } catch (ExitException e) {
                break;
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }

        // Goodbye
        ui.showGoodbye();
    }
}
