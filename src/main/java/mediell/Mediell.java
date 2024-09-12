package mediell;

/** The main class Mediell. */
public class Mediell {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;
    private Executor executor;
    private PictureUi pictureUi;

    public Mediell() {
        storage = new Storage();
        taskList = storage.loadData();
        ui = new Ui();
        parser = new Parser();
        executor = new Executor();
        pictureUi = new PictureUi();
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

    /**
     * Generates a image for the response to the user's chat message.
     */
    public PictureUi.PictureType getImage(String input) {
        try {
            Instruction instruction = parser.getInstruction(input);
            return pictureUi.getPicture(instruction);
        } catch (Exception ignored) {
            return pictureUi.getDefaultPicture();
        }
    }
}
