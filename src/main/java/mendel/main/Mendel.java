package mendel.main;

import java.util.Scanner;

import mendel.frontend.Parser;
import mendel.frontend.UI;
import mendel.mendelexception.ServerError;
import mendel.mendelexception.MendelException;

public class Mendel {
    private final Parser taskManager;
    private final UI ui;

    public Mendel() {
        this.taskManager = new Parser();
        this.ui = new UI();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String currAction = "hello";

        ui.showWelcome();
        while (!currAction.equals("bye")) {
            currAction = sc.nextLine().trim();
            try {
                ui.preetyPrint(taskManager.manage(currAction));
            } catch (MendelException e) {
                ui.preetyPrint(e.toString());
            } catch (ServerError e) {
                ui.preetyPrint(e.toString());
                taskManager.manage("bye");
                currAction = "bye";
            }
        }
    }

    public static void main(String[] args) {
        new Mendel().run();
    }
}
