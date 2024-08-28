package jude;

import jude.command.Command;

/**
 * Represents a jude.Jude, the personal assistant chatbot.
 * <p>
 * This class helps a person to keep track of various things.
 *
 * @author Judy Park
 **/
public class Jude {

    String name = "jude.Jude";
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    public Jude(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (JudeException je) {
            ui.showError(je);
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Jude("data/jude.txt").run();
    }

    public void run() {

        ui.startChat();
        boolean isExit = false;

        while (!isExit) {

            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JudeException je) {
                ui.showError(je);
            } finally {
                ui.showLine();
            }
        }
        ui.endChat();
    }

}


