package ipman;

import ipman.commands.Command;
import ipman.commands.Context;
import ipman.commands.ExitException;
import ipman.models.TaskList;
import ipman.models.TasksFileManager;
import ipman.ui.CliUi;
import ipman.ui.Ui;


public class IpMan {
    private static final Ui ui = new CliUi();
    private static final TaskList tasks = new TaskList();
    private static final TasksFileManager fileManager = new TasksFileManager("./data/ipman.txt");
    private static final Context context = new Context(tasks, ui, fileManager);

    public static void main(String[] args) {
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
