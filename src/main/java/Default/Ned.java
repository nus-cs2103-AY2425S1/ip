package Default;

import Exceptions.NedException;
import Tasks.Task;

import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Paths;
public class Ned {
    private Storage storage;

    private Ui ui;

    private ArrayList<Task> listOfText;
    public static final String cachedTasksPath = Paths.get("src", "data", "cachedTasks.txt").toString();
    public Ned(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.listOfText = this.storage.load();
        } catch (NedException e){
            ui.showLoadingError();
            this.listOfText = new ArrayList<>();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        this.checkCommands();
        ui.showByeMessage();
    };
    public static void main(String[] args) {
        new Ned(Ned.cachedTasksPath).run();
    }

    private void checkCommands() {
        System.out.println("\n");
        CommandManager commandParser = new CommandManager(this.listOfText);
        Scanner inputDetector = new Scanner(System.in);
        FlagWrapper flag = new FlagWrapper(true);
        while (flag.getStatus()) {
            String nextInput = inputDetector.nextLine();
            try {
                commandParser.processCommand(nextInput, flag);
            } catch (NedException e) {
                Ui.print(e.getMessage());
                Ui.showCommandMessage();
            }
        }
    }
}
