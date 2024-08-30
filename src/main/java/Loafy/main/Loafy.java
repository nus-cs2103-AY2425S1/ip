package loafy.main;

import java.util.Scanner;

import loafy.command.Command;
import loafy.loafyexception.LoafyException;
import loafy.parser.Parser;
import loafy.storage.Storage;
import loafy.tasklist.TaskList;
import loafy.ui.Ui;

public class Loafy {
    private TaskList tasks;
    private final Ui ui;

    public Loafy(String filePath) {
        this.ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage, storage.getList());
        } catch (LoafyException e) {
            ui.showStartError(e);
            this.tasks = new TaskList(storage);
        }
    }

    public void run() {
        this.ui.showGreeting();

        Scanner input = new Scanner(System.in);
        boolean exit = false;

        while (! exit) {
            System.out.print("You: ");
            String line = input.nextLine();
            try {
                Command command = Parser.parse(line);
                command.execute(this.tasks, this.ui);
                exit = command.isExit();
            } catch (LoafyException e) {
                this.ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Loafy("./data/loafy.txt").run();
    }
}