import config.Config;
import data.CSVTaskDAO;
import data.TaskDAO;
import features.command.Command;
import features.command.CommandHandler;
import features.task.TaskManagement;
import features.ui.Ui;
import utils.Utils;

import java.util.*;

public class Susan {
    private TaskManagement tm;
    private CommandHandler ch;
    private Scanner sc;
    private Ui ui;

    private Susan(TaskDAO taskDAO) {
        tm = new TaskManagement(taskDAO);
        ch = new CommandHandler(tm);
        ui = new Ui(new Scanner(System.in));
    }

    public static Susan init() throws Exception {
        TaskDAO taskDAO = new CSVTaskDAO();
        return new Susan(taskDAO);
    }

    public static void main(String[] args) {
        try {
            Susan s = Susan.init();
            s.run();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void run() {
        ui.intro();

        Command command = ui.readCommand();
        while (!command.isExitCommand()) {
            ch.handleCommand(command);
            ui.updateCommand(command);
        }

        ui.outro();
    }
}

