package duke;

import java.util.Objects;

/**
 * Represents a Duck chatbot object.
 */
public class Duck {
    private TaskList tasklist;
    //private static String previousCommand;
    private static Undo undo;
    Duck() throws DuckException {
        tasklist = new TaskList(Storage.load(), Storage.loadNum());
        undo = new Undo();
        //previousCommand = "";
    }

    /**
     * Responds to user input.
     *
     * @param input user input to be responded to.
     * @return String response from Duck.
     * @throws DuckException if command given is not valid.
     */
    public String getResponse(String input) throws DuckException {
        assert !input.isEmpty(): "User input cannot be empty";
        return Parser.parseCommand(tasklist, input);
    }

}

