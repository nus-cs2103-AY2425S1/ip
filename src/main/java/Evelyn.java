import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Evelyn {

    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    public Evelyn(String filePath) {
        this.taskList = new TaskList(new Storage(filePath));
        this.parser = new Parser(this.taskList);
        this.ui = new Ui(this.parser);
    }

    public void run() {
        this.ui.start();
    }

    public static void main(String[] args) throws IOException {
        String dataFilePath = "src/main/data/evelyn.txt";
        new Evelyn(dataFilePath).run();
    }

}
