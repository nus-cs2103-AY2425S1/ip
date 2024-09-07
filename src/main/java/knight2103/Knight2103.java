package knight2103;

import knight2103.command.InstructionInvalid;
import knight2103.command.Parser;
import knight2103.command.Command;
import knight2103.command.MissingCommand;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;

public class Knight2103 {
    private String name;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Knight2103(String filePath) {
        this.name = "Knight2103";
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) { // file be loaded regardless
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String output;
            try {
                Command c = Parser.parse(input).orElseThrow(() -> new MissingCommand());
                output = c.execute(tasks, ui, storage);
                return this.ui.showLine() + output;
            } catch (MissingCommand e) {
                return e.getMessage();
            } catch (InstructionInvalid e) {
                return "Instruction wrong format.";
            }
    }
}