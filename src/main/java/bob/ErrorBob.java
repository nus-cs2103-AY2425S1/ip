package bob;

import java.io.IOException;

/**
 * Represents a non-functioning Bob that only replies with the error he was instantiated with.
 */
public class ErrorBob extends Bob {

    private String error;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public ErrorBob(String error) {
        ui = new Ui();
        this.error = error;
    }

    @Override
    public String getResponse(String input) {
        return this.error;
    }

    public void run() {
        ui.showBar();
        ui.show(error);
        ui.showBar();
    }

}