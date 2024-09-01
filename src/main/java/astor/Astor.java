package astor;

import astor.command.Command;
import astor.exception.*;

import java.io.IOException;

public class Astor {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private static final String DESTINATION_STORAGE = "./src/main/data/astor.Astor.txt";


    public Astor(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.getData());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.process(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (AstorException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("An error occurred while writing to the file.");
            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        Astor astor = new Astor(Astor.DESTINATION_STORAGE);
        astor.run();
    }

}
