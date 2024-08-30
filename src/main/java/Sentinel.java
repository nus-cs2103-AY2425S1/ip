import java.time.LocalDateTime;

public class Sentinel {
    private static SentinelList list;
    private Ui ui;

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
