package gallium.main;


import gallium.command.Command;

/**
 * The main class for Gallium.
 * It handles the initialization and running of the program.
 */
public class Gallium {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private boolean isExit;

    /**
     * Constructs a Gallium object with the specified file path for storage.
     *
     * @param filePath The file path where tasks will be saved and loaded from.
     */
    public Gallium(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load(ui));
        isExit = false;
    }

    /**
     * Runs the Gallium program, reading and executing user commands in a loop
     * until the bye command is executed.
     */
    public void run() {
        while (!isExit) {
            String message = "";
            Parser parser = new Parser(ui);
            Command c = parser.parse(message);
            try {
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (GalliumException e) {
                ui.showGalliumException(e);
            }
        }
        storage.writeFile(ui);
        ui.closeScanner();
    }

    public String getResponse(String input) {
        if (isExit) {
            return "";
        }
        try {
            ui.resetOutput();
            Parser parser = new Parser(ui);
            Command c = parser.parse(input);
            c.execute(taskList, ui, storage);
            isExit = c.isExit();
            return ui.getOutput();
        } catch (GalliumException e) {
            return e.getMessage();
        }
    }

    /**
     * The main entry point of Gallium.
     * 
     * @param args
     */
    public static void main(String[] args) {
        new Gallium("data/gallium.txt").run();

    }
}
