package mediell;

import javafx.application.Application;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/** The main class Mediell. */
public class Mediell {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Mediell() throws IOException {
        storage = new Storage();
        taskList = storage.loadData();
        ui = new Ui(taskList);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws IOException {
        String response = ui.main(input);
        if (Objects.equals(response, "")) {
            return ui.printFarewell();
        }
        storage.saveData(ui.getTasks());
        return response;
    }
}
