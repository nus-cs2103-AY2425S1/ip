package gallium.main;

import gallium.command.Command;

public class Gallium {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Gallium(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        // try {
        taskList = new TaskList(storage.load(ui));
        // } catch (GalliumException e) {
        // ui.showLoadingError();
        // taskList = new TaskList();
        // }
    }

    public void run() {
        boolean isExit = false;
        while (!isExit) {
            String Message = ui.readNextLine();
            parser = new Parser(ui, taskList);
            Command c = parser.parse(Message);
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

    public static void main(String[] args) {
        new Gallium("data/gallium.txt").run();

    }
}
