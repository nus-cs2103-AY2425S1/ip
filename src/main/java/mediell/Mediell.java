package mediell;

/** The main class Mediell. */
public class Mediell {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;
    private Executor executor;

    public Mediell() {
        storage = new Storage();
        taskList = storage.loadData();
        ui = new Ui();
        parser = new Parser();
        executor = new Executor();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Instruction instruction = parser.getInstruction(input);
            instruction = executor.executeInstruction(instruction, taskList);
            return ui.getMessage(instruction, taskList);
        } catch (Exception e) {
            return ui.handleError(e);
        }
    }
}
