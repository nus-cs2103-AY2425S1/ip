package count;

import java.io.FileNotFoundException;
import java.util.Scanner;

import count.action.Action;
import count.action.Deactivate;
import count.exception.CountException;
import count.exception.IncorrectFormatException;

/**
 * Count is a lightweight helper with an inbuilt to-do list
 * @author Kieran Koh Jun Wei
 */
public class Count {
    private TaskList ls;
    private boolean isOn;
    private Scanner sc;
    private UI ui;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor for Count object
     * @param filePath String filePath in which Count will read and save to
     */
    public Count(String filePath) {
        this.isOn = true;
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

        while (this.isOn) {
            String command = sc.nextLine();
            try {
                Action curr = parser.parse(command);
                if (curr instanceof Deactivate) {
                    this.isOn = false;
                    this.sc.close();
                }
            } catch (CountException e) {
                ui.reply(e.getMessage());
            }
        }
    }

    /**
     * Gets response from the input to be sent to gui
     * @param input to be parsed
     * @return String to be shown to the user
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Action curr = parser.parse(input);
            response = curr.run();
        } catch (CountException e) {
            response = e.getMessage();
        }
        return response;
    }
    public static void main(String[] args) {
        Count c = new Count("./Count.txt");
        c.start();
    }
}
