package elara.main;

import elara.command.Command;
import elara.command.ExitCommand;

import elara.parser.Parser;

import elara.storage.Storage;

import elara.task.InvalidInputException;
import elara.task.TaskList;

import elara.ui.Ui;

public class Elara {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Elara(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            String input = ui.readInput();

            try {
                Command command = Parser.parse(input);
                command.execute(taskList, ui, storage);
                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (InvalidInputException e) {
                ui.showInvalidCommandMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Elara("./data/elara.main.Elara.txt").run();
    }
}