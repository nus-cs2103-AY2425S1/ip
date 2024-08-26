package soju;

import soju.commands.ByeCommand;
import soju.commands.Command;

import java.util.Scanner;
public class Soju {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;


    public Soju() {
        ui = new Ui();
        storage = new Storage("./data/soju.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (SojuException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public static void main(String[] args) {
        Soju soju = new Soju();
        soju.run();
    }
    public void run() {
        ui.greet();
        while (true) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                ui.printHorizontalLine();
                storage.saveToFile(tasks);
                if (command instanceof ByeCommand) {
                    break;
                }
            } catch (SojuException e) {
                ui.printError(e);
            } finally {
                ui.printHorizontalLine();
            }
        }
    }
}
