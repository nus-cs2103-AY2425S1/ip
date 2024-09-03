package evelyn;

import evelyn.command.ui.gui.Gui;
import javafx.application.Application;

import evelyn.command.Parser;
import evelyn.command.Storage;
import evelyn.command.TaskList;
import evelyn.command.ui.textbased.TextBasedUi;

import java.io.IOException;

public class Evelyn {

    private TextBasedUi ui;
    private TaskList taskList;
    private Parser parser;
    private static final String DEFAULT_FILE_PATH = "src/main/data/evelyn.txt";

    public Evelyn() {
        this.taskList = new TaskList(new Storage(DEFAULT_FILE_PATH));
        this.parser = new Parser(this.taskList);
//        this.ui = new TextBasedUi(this.parser);
    }

    public String getResponse(String input) {
        return "Evelyn heard: " + input;
    }


    public static void main(String[] args) throws IOException {
        String dataFilePath = "src/main/data/evelyn.txt";
        Application.launch(Gui.class, args);
    }

}
