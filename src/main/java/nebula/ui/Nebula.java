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

    /**
     * Constructs a new Nebula instance.
     * Initializes the user interface, storage, task list, and parser.
     *
     * @param filePath The path of the file where task data is stored.
     */
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

    /**
     * Continuously reads user commands, processes them, and executes the corresponding actions
     * until the exit command is given.
     *
     * @throws IOException If there is an issue reading from or writing to files.
     */
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
     * The entry point of the application.
     * Initializes the Nebula application with the provided file path and starts it.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException If there is an issue during the execution of the application.
     */
    public static void main(String[] args) throws IOException {
        new Nebula("./data/nebulaTaskList.txt").run();
    }



}
