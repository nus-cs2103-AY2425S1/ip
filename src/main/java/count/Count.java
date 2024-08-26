package count;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import count.action.Action;
import count.exception.*;
public class Count {
    private TaskList ls;
    private boolean on;
    private Scanner sc;
    private UI ui;
    private Storage storage;
    private Parser parser;

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
        this.parser = new Parser(this.ls);
    }

    private void start() {
        ui.greet();

        while (this.on) {
            String command = sc.nextLine();
            try {
                Action curr = parser.parse(command);
                String message = curr.run();
                if (message.equals("Bye. Hope to see you again soon!")) {
                    this.on = false;
                    this.sc.close();
                }

                ui.reply(message);
            } catch (CountException e) {
                ui.reply(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Count c = new Count("./Count.txt");
        c.start();
    }
}
