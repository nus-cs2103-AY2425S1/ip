package count;

import java.io.FileNotFoundException;

import count.action.Action;
import count.action.Deactivate;
import count.exception.CountException;
import count.exception.IncorrectFormatException;

/**
 * Count is a lightweight helper with an inbuilt to-do list
 * @author Kieran Koh Jun Wei
 */
public class Count {
    private TaskList ls;
    private boolean isOn;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor for Count object
     * @param filePath String filePath in which Count will read and save to
     */
    public Count(String filePath) {
        this.isOn = true;
        this.storage = new Storage(filePath);
        try {
            this.ls = new TaskList(this.storage.load());
        } catch (FileNotFoundException | IncorrectFormatException e) {
            this.ls = new TaskList();
        }
        this.parser = new Parser(this.ls, filePath);
    }

    /**
     * Gets response from the input to be sent to gui
     * @param input to be parsed
     * @return String to be shown to the user
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Action curr = parser.parse(input);
            if (curr instanceof Deactivate) {
                this.isOn = false;
            }
            response = curr.run();
        } catch (CountException e) {
            response = e.getMessage();
        }
        return response;
    }

    /**
     * Getter for boolean variable isOn
     * @return boolean isOn
     */
    public boolean getOnStatus() {
        return this.isOn;
    }
}
