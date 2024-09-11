package duke;

/**
 * Represents a Duck chatbot object.
 */
public class Duck {
    private TaskList tasklist;
    Duck() throws DuckException {
        tasklist = new TaskList(Storage.load(), Storage.loadNum());
    }

    /**
     * Responds to user input.
     *
     * @param input user input to be responded to.
     * @return String response from Duck.
     * @throws DuckException if command given is not valid.
     */
    public String getResponse(String input) throws DuckException {
        return Parser.parseCommand(tasklist, input);
    }
}

