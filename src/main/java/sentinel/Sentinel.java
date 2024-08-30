package sentinel;

import sentinel.command.ByeCommand;
import sentinel.command.Command;
import sentinel.exception.SentinelException;
import sentinel.storage.FileLoader;
import sentinel.storage.FileWriter;
import sentinel.ui.Ui;
import sentinel.utils.Parser;
import sentinel.utils.SentinelList;

public class Sentinel {
    private static SentinelList list;
    private final Ui ui;

    public Sentinel() {
        ui = new Ui();
        try {
            list = new FileLoader().loadTasks();
        } catch (Exception e) {
            ui.showError(e);
            list = new SentinelList();
        }
    }

    public void run() {
        ui.showWelcome();
        Command command = null;
        do {
            try {
                String input = ui.readLine();
                ui.showLine();
                Sentinel.CommandType commandType = Parser.parseForCommand(input);
                command = Command.createCommand(commandType, ui, list);
                command.execute(input);
            } catch (IllegalArgumentException e) {
                ui.showUnrecognisedCommand();
            } catch (SentinelException e){
//                sentinel.ui.showError(e);
            }
            ui.showLine();
            new FileWriter(list).saveTasks();
        } while (!(command instanceof ByeCommand));
    }

    public static void main(String[] args) {
        new Sentinel().run();
    }

    public enum CommandType {
        list, mark, unmark, delete, todo, deadline, event, help, bye
    }
}
