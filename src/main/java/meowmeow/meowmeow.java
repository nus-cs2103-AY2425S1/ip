package meowmeow;

import java.io.IOException;

public class meowmeow {
    private Saving saver;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public meowmeow(String filePath) {
        this.saver = new Saving(filePath);
        this.ui = new Ui();
    }

    public void run() throws IOException {
        this.saver.getData();
        this.taskList = saver.getTaskList();
        String input = Ui.start();
        this.parser = new Parser(this.taskList, this.saver, this.ui, input);
        this.parser.parse();
    }

    public static void main(String[] args) throws IOException {
        new meowmeow("./data/duke.txt").run();
    }
}