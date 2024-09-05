package monobot;

import monobot.command.Command;
import monobot.exception.MonoBotException;
import monobot.util.Parser;
import monobot.util.Storage;
import monobot.util.TaskList;
import monobot.util.Ui;

import java.util.Scanner;

public class MonoBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public MonoBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MonoBotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreeting();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = sc.nextLine();
                Command command = Parser.parseCommand(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (MonoBotException e) {
                ui.printError(e.toString());
            }
        }
        sc.close();
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    public Ui getUi() {
        return this.ui;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public static void main(String[] args) {
        MonoBotGUI.main(args);
    }
}