package Bwead;

import java.io.IOException;

public class Bwead {

    private History history;
    private TaskList tasks;
    private Ui ui;

    public static String name = "Bwead";

    public Bwead(String filePath) throws IOException{
        ui = new Ui();
        history = new History(filePath);
        try {
            tasks = new TaskList(history.load());
        } catch (BweadException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
        ui.set(history, tasks);
    }

    public void run() throws IOException {
        System.out.println("Hello from " + name + "!");
        ui.handleCommands();
    }

    public static void main(String[] args) throws BweadException, IOException {
        new Bwead("./src/main/java/Bwead/historyFile.txt").run();
    }
}
