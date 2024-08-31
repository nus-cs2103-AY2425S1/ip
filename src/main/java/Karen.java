import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.*;

import main.java.commands.Command;
import main.java.util.Parser;
import main.java.util.Storage;
import main.java.util.Ui;

public class Karen {
    private TaskList taskList;
    private Ui ui;

    public Karen() {
        this.taskList = new TaskList();
        Storage.initFile();
        Storage.readFile(this.taskList);
        this.ui = new Ui();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandString = this.ui.readInput();
                Command c = Parser.parse(commandString, this.ui);
                if (c != null) { //c is null when input is invalid
                    c.execute(this.taskList, this.ui);
                    isExit = c.isExit();
                }
            } catch (Exception e) {
                System.out.println("Exception occurred in main loop");
                System.out.println(e);
                return;
            }
        }
    }

    public static void main(String[] args) {
        new Karen().run();
    }
}
