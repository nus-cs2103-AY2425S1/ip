package yapper;

import java.io.File;

/**
 * Represent a Yapper
 */
public class Yapper {
    private static Ui ui;
    private static Parser parser;
    private static Storage storage;
    private static TaskList taskList;

    /**
     * Creates an instance of Yapper.
     *
     * @param fileName String of file path to load and write the history of tasks to.
     */
    public Yapper(String fileName) {
        File file = new File(fileName);
        storage = new Storage(file);
        taskList = storage.loadHistory();
        parser = new Parser(taskList);
        ui = new Ui(parser);
    }

    public static void main(String[] args) {
        String fileName = "./src/main/java/Yapper/YapperHistoryFile";
        new Yapper(fileName).run();
    }

    /**
     * Runs the program.
     */
    @SuppressWarnings("checkstyle:JavadocContentLocation")
    public void run() {
        System.out.println("Hello! I'm Yapper\nWhat can I do for you?\n");
        taskList.returnList();
        ui.readInput();
    }

    public String guiRun(String input) {
        return ui.readGuiInput(input);
    }
}

