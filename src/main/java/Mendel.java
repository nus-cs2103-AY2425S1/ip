import java.util.Scanner;

import mendel.frontend.Parser;
import mendel.frontend.UI;
import mendel.mendelexception.ServerError;
import mendel.mendelexception.MendelException;

public class Mendel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String currAction = "hello";
        Parser taskManager = new Parser();
        UI ui = new UI();
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
}
