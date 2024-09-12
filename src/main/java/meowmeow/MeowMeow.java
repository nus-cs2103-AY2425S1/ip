package meowmeow;

import java.io.IOException;

public class MeowMeow {
    private Storage saver;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public MeowMeow(String filePath) {
        this.saver = new Storage(filePath);
        this.ui = new Ui();
    }

    public void run() throws IOException {
        this.saver.getData();
        this.tasks = saver.getTaskList();
        String input = Ui.start();
        this.parser = new Parser(this.tasks, this.saver, this.ui, input);
        this.parser.parse();
    }

    public static void main(String[] args) throws IOException {
        new MeowMeow("./data/meowmeow.txt").run();
    }
}