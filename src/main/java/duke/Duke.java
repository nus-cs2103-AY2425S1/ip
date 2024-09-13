package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(), storage);
        } catch (FileNotFoundException | ParseException e) {
            ui.showError("Error with input file!");
            tasks = new TaskList(new ArrayList<>(), storage);
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.ParseCommand(fullCommand);
                if (c != null) {
                    c.execute(tasks, ui, storage);
                }
                isExit = Command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (ParseException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getResponse(String text) {
        try {
            Command command = Parser.ParseCommand(text);
            if (command != null) {
                return command.execute(tasks, ui, storage);
            }
            return "Command does not exist!";
        } catch (DukeException e) {
            return e.getMessage();
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }

}
