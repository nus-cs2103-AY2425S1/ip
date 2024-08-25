package duke;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.EmptyDescriptionException;
import duke.exception.UnknownCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

public class Duke {
    private static final String FILE_PATH = "./duke/data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();

        boolean isRunning = true;
        while (isRunning) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isRunning = !command.isExit();
            } catch (EmptyDescriptionException | UnknownCommandException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("An error occurred while saving your tasks.");
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}