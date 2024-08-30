import Exception.EmptyDescriptionException;
import Tools.Parser;
import Tools.Storage;
import Tools.TaskList;
import Tools.Ui;

import java.io.FileNotFoundException;


public class ChatgptMoreOOP {

    public static void main(String[] args) throws EmptyDescriptionException, FileNotFoundException {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");
        TaskList tasks= storage.load();
        Parser parser = new Parser(tasks, storage, ui);

        ui.showWelcome();
        while (ui.hasNextLine()) {
            String line = ui.readCommand();
            parser.parse(line);
        }
    }
}
