package duke;

import duke.command.Command;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Represents the Bob Chatbot application.
 */
public class Bob {
    private TaskList tasks;

    private Ui ui;

    private Storage storage;

    public Bob(String path) {
        this.ui = new Ui();
        this.storage = new Storage(path);
        try {
            ArrayList<Task> tasks = this.storage.load();
            this.tasks = new TaskList(tasks);
        } catch (BobException e) {
            ui.write(e.toString());
        }
    }

    public void run() {
        ui.greet();

        boolean isExit = false;
        while(!isExit) {
            try {
                String input = ui.read();
                Command command = Parser.parseInput(input);
                command.execute(this.tasks, this.ui, this.storage);
                isExit = command.isExit();
            } catch (BobException e) {
                ui.write(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Bob("./data/bob.txt").run();
    }
}
