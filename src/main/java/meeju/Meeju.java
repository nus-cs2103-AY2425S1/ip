package meeju;

/**
 * Main class of the program. It serves as the entry point to the program.
 */
public class Meeju {
    private Storage storage = new Storage();
    private TaskList taskList = new TaskList(storage);
    private Parser parser = new Parser();

    public String getResponse(String instruction) {
        return parser.parse(taskList, instruction);
    }
}
