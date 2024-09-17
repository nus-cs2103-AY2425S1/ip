package tomo;

import command.Command;
import exception.ToMoException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Handles everything about the conversation with user
 */
public class ToMo {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of the chatbot
     * @param fileName The file to load and store tasks
     */

    public ToMo(String fileName) {
        parser = new Parser();
        ui = new Ui();
        storage = new Storage(fileName);
        try {
            tasks = storage.load();
            ui.println("Successfully loaded " + tasks.size() + " tasks");
        } catch (ToMoException e) {
            ui.println(e);
            tasks = new TaskList();
            ui.println("Successfully loaded 0 task");
        }
    }

    /**
     * Closes the conversation
     */
    void close() {
        try {
            storage.store(tasks);
        } catch (ToMoException e) {
            ui.println(e);
        }
        ui.close();
    }

    /**
     * Interacts with user
     */
    public void run() {
        while (true) {
            try {
                String cmdline = ui.nextLine();
                Command command = parser.parse(cmdline);
                command.execute(tasks, ui);
                if (command.isExit()) {
                    break;
                }
            } catch (ToMoException e) {
                ui.println(e);
            }
        }

        close();
    }

    public static void main(String[] args) {
        new ToMo("../../../data/ToMo.txt").run();
    }
}
