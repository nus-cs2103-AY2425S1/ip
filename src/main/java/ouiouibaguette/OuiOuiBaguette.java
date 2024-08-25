package ouiouibaguette;

import command.Command;

import parser.Parser;

import storage.FileStorage;
import storage.Storage;

import tasklist.TaskList;

import ui.CommandLineUI;


public class OuiOuiBaguette {

    private Storage storage;
    private TaskList tasks;
    private CommandLineUI ui;
    private Parser parser;

    public OuiOuiBaguette(String dirPath) {
        ui = new CommandLineUI();
        storage = new FileStorage(dirPath);
        tasks = new TaskList(storage);
        parser = new Parser();
    }

    public void run() {
        ui.greet();
        System.out.println();

        boolean isExit = false;

        while (!isExit) {
            String cmd = ui.readCommand();

            // ui.showDivider();

            try {
                Command c = parser.parseCommand(cmd);
                if (c.isExit()) {
                    break;
                }

                ui.showDivider();

                c.execute(tasks, ui);

                ui.showDivider();

            } catch (OuiOuiBaguetteException e) {
                ui.showDivider();
                ui.speakLine(e.getMessage());
                ui.showDivider();
            }

            System.out.println();

        }

        ui.farewell();
        System.out.println();
    }
}
