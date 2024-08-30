package wenjieBot;

import wenjieBot.commands.Command;
import wenjieBot.exceptions.DukeException;

import java.util.Scanner;

public class WenJie {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public WenJie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(scanner);
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }
    public static void main(String[] args) {
        String filePath = "./src/main/java/data/wenjie.txt";
        new WenJie(filePath).run();
    }




}
