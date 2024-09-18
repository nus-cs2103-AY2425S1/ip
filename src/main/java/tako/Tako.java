package tako;

import storage.Storage;
import ui.Ui;

/**
 * Represents the chatbot where the main part of the program runs
 */
public class Tako {
    private Storage storage;

    public Tako() {
        storage = new Storage("data/Tako.txt");
        storage.load();
    }
}
