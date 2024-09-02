package main;

import java.util.Scanner;

/**
 * This class is used to control the main logical flow of the Bean program
 */
public class Bean {
    private TaskList taskList;

    private Storage storage;
    private Ui ui;
    private Parser parser;

    public static void main(String[] args) {
        Bean bean = new Bean("src\\main\\java\\data\\tasks.txt");
        bean.run();
    }

    /**
     * Creates the program with the given storage path
     * @param path location of the text file storage
     */
    public Bean(String path) {
        storage = new Storage(path);
        taskList = new TaskList(storage.readStorage());
        ui = new Ui();
        parser = new Parser(taskList, ui, storage);
    }

    /**
     * Runs the logic of the program
     */
    public void run() {
        ui.greetingMessage();
        Scanner scanner = new Scanner(System.in);
        Boolean status = true;
        while (status) {
            String response = scanner.nextLine();
            status = parser.parse(response);
        }
    }

}
