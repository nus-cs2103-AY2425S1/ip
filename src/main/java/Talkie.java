import talkie.command.Command;
import talkie.components.Parser;
import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieException;
import talkie.task.TaskList;

public class Talkie {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Talkie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(this.storage.loadData());
        } catch (TalkieException e) {
            ui.showTalkieException(e);
            tasks = new TaskList();
        }
    }

    // Runs the main program
    public void runTalkie() {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = this.ui.readCommand();
                Command c = Parser.getCommand(input);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (TalkieException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        // Start of Talkie
        new Talkie("./data/Talkie.txt").runTalkie();
    }
}
