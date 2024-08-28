package count;

import java.io.FileNotFoundException;
import java.util.Scanner;

import count.action.Action;
import count.action.Deactivate;
import count.exception.IncorrectFormatException;
import count.exception.CountException;

/**
 * Count is a lightweight helper with an inbuilt to-do list
 * @author Kieran Koh Jun Wei
 */
public class Count {
    private TaskList ls;
    private boolean on;
    private Scanner sc;
    private UI ui;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor for Count object
     * @param filePath String filePath in which Count will read and save to
     */
    public Count(String filePath) {
        this.on = true;
        this.sc = new Scanner(System.in);
        this.ui = new UI();
        this.storage = new Storage(filePath);
        try {
            this.ls = new TaskList(this.storage.load());
        } catch (FileNotFoundException e) {
            this.ls = new TaskList();
        } catch (IncorrectFormatException e) {
            this.ls = new TaskList();
            this.ui.reply(e.getMessage());
        }
        this.parser = new Parser(this.ls, filePath);
    }

    /**
     * start is a method that turns the Count program on,
     * It calls ui.greet() and passes all commands typed to the parser
     * If the command parsed returns a Deactivate object, Count turns off
     */
    private void start() {
        ui.greet();

        while (this.on) {
            String command = sc.nextLine();
            try {
                Action curr = parser.parse(command);
                String message = curr.run();
                if (curr instanceof Deactivate) {
                    this.on = false;
                    this.sc.close();
                }

                ui.reply(message);
            } catch (CountException e) {
                ui.reply(e.getMessage());
            }
        }
    }

    /**
     * main method for Count.java
     * starts a Count instance with filePath of the root directory
     * @param args
     */
    public static void main(String[] args) {
        Count c = new Count("./Count.txt");
        c.start();
    }
}
