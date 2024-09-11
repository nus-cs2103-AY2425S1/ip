package duke;

/**
 * Represents a Duck chatbot object.
 */
public class Duck {
    private TaskList cmds;
    Duck() throws DuckException {
        cmds = new TaskList(Storage.load(), Storage.loadNum());
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
        return Parser.parseCommand(cmds, input);
    }
}

