package Yapper;

import java.io.File;

public class Yapper {
    public static UI ui;
    public static Parser parser;
    public static Storage storage;
    public static TaskList taskList;

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
        ui = new UI(parser);
    }

    public static void main(String[] args) {
        String fileName = "./src/main/java/Yapper/YapperHistoryFile";
        (new Yapper(fileName)).run();
    }

    public void run() {
        System.out.println("Hello! I'm Yapper\nWhat can I do for you?\n");
        taskList.returnList();
        ui.readInput();
    }
}

