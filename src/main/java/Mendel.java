import java.util.Scanner;

import mendel.mendelexception.ServerError;
import mendel.mendelexception.MendelException;

public class Mendel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String currAction = "hello";
        TaskManager taskManager = new TaskManager();
        UI ui = new UI();
        ui.showWelcome();
        while (!currAction.equals("bye")) {
            currAction = sc.nextLine().trim();
            try {
                ui.preetyPrint(taskManager.manage(currAction));
            } catch (MendelException e) {
                ui.preetyPrint(taskManager.manage(e.toString()));
            } catch (ServerError e) {
                ui.preetyPrint(taskManager.manage(e.toString()));
                taskManager.manage("bye");
                currAction = "bye";
            }
        }
    }
}
