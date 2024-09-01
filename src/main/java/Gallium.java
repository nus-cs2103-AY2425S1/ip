public class Gallium {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Gallium(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        // try {
        taskList = new TaskList(storage.load());
        // } catch (GalliumException e) {
        // ui.showLoadingError();
        // taskList = new TaskList();
        // }
    }

    public void run() {
        String Message = ui.readNextLine();
        parser = new Parser(ui, taskList);
        parser.run(Message);
        storage.writeFile(ui);
        ui.printByeMessage();
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Gallium("data/gallium.txt").run();

    }
}
