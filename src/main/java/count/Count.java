package count;

import java.io.FileNotFoundException;

import count.action.Action;
import count.action.Deactivate;
import count.exception.CountException;
import count.exception.IncorrectFormatException;
import count.exception.InvalidTimelineException;

/**
 * Count is a lightweight helper with an inbuilt to-do list
 * @author Kieran Koh Jun Wei
 */
public class Count {
    private TaskList taskList;
    private boolean isOn;
    private Storage storage;
    private Parser parser;

    /**
     * Constructs Count object
     * @param filePath String filePath in which Count will read and save to
     */
    public Count(String filePath) {
        this.isOn = true;

        this.storage = new Storage(filePath);

        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (FileNotFoundException | IncorrectFormatException | InvalidTimelineException e) {
            this.taskList = new TaskList();
        }
        this.parser = new Parser(this.taskList, filePath);
    }

    /**
     * Gets response from the input to be sent to gui
     * @param input to be parsed
     * @return String to be shown to the user
     */
    public String getResponse(String input) {
        assert this.isOn = true;
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

}
