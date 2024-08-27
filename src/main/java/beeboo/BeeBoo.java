package beeboo;

import beeboo.command.Command;
import beeboo.components.Parser;
import beeboo.components.Storage;
import beeboo.components.TaskList;
import beeboo.components.Ui;
import beeboo.exception.BeeBooExceptions;
import beeboo.exception.InvalidCommandException;

public class BeeBoo {

    //Arraylist for the tasklist
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    //Initialise tasklist when beeboo is created
    public BeeBoo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BeeBooExceptions e) {
            ui.loadingError();
            tasks = new TaskList();
        }
    }


    public void run() {
        Ui ui = new Ui();

        ui.showWelcomeMessage();
        boolean isExit = false;

        //Prompts user while user doesn't enter bye
        while (!isExit) {
            try {
                String fullCommand = ui.handleCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                ui.chatBox("Invalid Command!Me no understand");
            } catch (BeeBooExceptions e) {
                ui.chatBox(e.toString());
            }

        }

    }


    public static void main(String[] args) {
        new BeeBoo("./data/beeboo.txt").run();
    }
}