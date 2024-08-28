
import java.io.IOException;
import java.util.ArrayList;

public class LeBron {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public LeBron(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        String input;
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                input = ui.getUserCommand();
                ui.showLine();
                Command command = parser.parse(input);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (LeBronException e) {
                ui.showLine();
                System.out.println(e.getMessage());
                ui.showLine();
            }
    }
}

    public static void main(String[] args) {
        new LeBron("./data/lebron.txt").run();
    }
}
