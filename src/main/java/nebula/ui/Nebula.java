package nebula.ui;

import nebula.exception.NebulaException;
import nebula.storage.Storage;
import nebula.command.Command;
import nebula.task.TaskList;

import java.io.*;

public class Nebula {

    private Ui ui;
    private TaskList tasks;
    private Parser parser;
    private Storage storage;

    public Nebula(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NebulaException e) {
            tasks = new TaskList();
        }
        parser = new Parser(filePath, tasks);
    }

    public void run() throws IOException {
        System.out.println(ui.greeting());
        boolean isExit = false;

        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                assert c != null;
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NebulaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry point of the application. Initializes the UI, task list, and parser,
     * then processes user commands in a loop until "bye" is entered
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) throws IOException {
        new Nebula("./data/nebulaTaskList.txt").run();
    }



}
