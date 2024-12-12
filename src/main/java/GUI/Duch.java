package gui;

import duch.Parser;
import duch.Storage;
import duch.Ui;
import duch.TaskList;




public class Duch {
    TaskList tasks = new TaskList();
    Ui ui = new Ui();
    Parser parser = new Parser(tasks);
    Storage storage = new Storage(tasks);

    public Duch() {
        storage.openFile();
    }

    /**
     * Gets the response to the user input.
     * 
     * @param cmd.
     * @return
     */
    public String getResponse(String cmd) {
        try {
            return parser.handleCommand(cmd);
        } catch (Exception e) {
            return e.getMessage();
        }
           
    }

    /**
     * Returns the welcome messgae
     * 
     * @return
     */
    public String getWelcomeString() {
        return "Hello I am Duch.\nWhat can I do for you?";
    }

    public void save() {
        storage.saveTasks();
    }
}
