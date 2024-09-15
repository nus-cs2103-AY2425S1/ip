package calebyyy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import calebyyy.exceptions.CalebyyyException;

/**
 * Represents the main Calebyyy object.
 */
public class Calebyyy {
    private Parser parser;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for Calebyyy.
     */
    public Calebyyy() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        taskList = new TaskList(ui);
        storage.loadTasks(taskList);
        parser = new Parser(this, taskList, storage, ui);
    }

    public void start() {
        parser.startCommandLoop();
    }

    public static void main(String[] args) {
        new Calebyyy().start();
    }

    public String getResponse(String input) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);

        try {
            parser.executeCommand(input);
        } catch (CalebyyyException e) {
            System.setOut(oldOut);
            return "Error: " + e.getMessage();
        }

        System.out.flush();
        System.setOut(oldOut);
        return baos.toString();
    }
}
