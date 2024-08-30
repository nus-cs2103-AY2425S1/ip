package evelyn;

import evelyn.command.Parser;
import evelyn.command.Storage;
import evelyn.command.TaskList;
import evelyn.command.Ui;

import java.io.IOException;

public class Evelyn {

    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    public Evelyn(String filePath) {
        this.taskList = new TaskList(new Storage(filePath));
        this.parser = new Parser(this.taskList);
        this.ui = new Ui(this.parser);
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        this.ui.start();
    }

    public static void main(String[] args) throws IOException {
        String dataFilePath = "src/main/data/evelyn.txt";
        new Evelyn(dataFilePath).run();
    }

}
