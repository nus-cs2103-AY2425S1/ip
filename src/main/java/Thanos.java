import java.util.Scanner;

import commands.ByeCommand;
import commands.Command;
import ui.Ui;
import exceptions.InvalidCommandException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;

public class Thanos {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    public Thanos(String fileName) {
        this.storage = new Storage(fileName);
        this.taskList = new TaskList(storage);
        this.ui = new Ui();
    }

    public void run() {
        this.ui.displayWelcome();

        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = scanner.nextLine();
                Command command = Parser.parse(userInput);
                command.execute(taskList, ui);
                isExit = command.isExit();
            } catch (InvalidCommandException e) {
                ui.display(e.getMessage() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        new Thanos("data.txt").run();
    }
}
